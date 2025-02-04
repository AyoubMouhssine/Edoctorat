package com.estf.edoctorat.controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.stream.Collectors;

import com.estf.edoctorat.config.CustomUserDetails;
import com.estf.edoctorat.config.GoogleTokenVerifier;
import com.estf.edoctorat.dtos.auth.*;
import com.estf.edoctorat.models.*;
import com.estf.edoctorat.services.CandidatService;
import com.estf.edoctorat.services.EmailService;
import com.estf.edoctorat.services.JwtService;
import com.estf.edoctorat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.estf.edoctorat.repositories.CandidatRepository;
import com.estf.edoctorat.repositories.PaysRepository;
import com.estf.edoctorat.repositories.ProfesseurRepository;
import com.estf.edoctorat.repositories.UserRepository;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private ProfesseurRepository professeurRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GoogleTokenVerifier googleTokenVerifier;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private PaysRepository paysRepository;
    @Autowired
    private CandidatRepository candidatRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;
    @Autowired
    CandidatService candidatService;

    private GoogleIdToken.Payload verifyGoogleToken(String idTokenString) throws GeneralSecurityException, IOException {
        return googleTokenVerifier.verify(idTokenString);
    }

    @PostMapping("/confirm-email/")
    public ResponseEntity<Map<String, String>> confirmEmail(@RequestBody EmailConfirmationRequest request) {
        if (userService.emailExists(request.getEmail())) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already in use"));
        }

        User user = userService.createUser(request.getEmail(), request.getNom(), request.getPrenom());
        String token = jwtService.generateToken(new CustomUserDetails(user,null));

        String confirmationUrl = request.getOrigin() + "?token=" + token;

        emailService.sendConfirmationEmail(user.getEmail(), confirmationUrl);

        return ResponseEntity.ok(Map.of("message", "Confirmation email sent"));
    }

    @PostMapping("/verify-token/")
    public ResponseEntity<PreRegistrationResponse> verifyToken(@RequestBody TokenRequest tokenRequest) {
        try {
            String token = tokenRequest.getToken();

            String username = jwtService.extractUsername(token);
            Optional<User> optUser = userService.getUserByEmail(username);
            if (optUser.isEmpty()) {
                return ResponseEntity.status(400).body(null);
            }

            User user = optUser.get();
            if (jwtService.isTokenValid(token, new CustomUserDetails(user, null))) {
                PreRegistrationResponse preRegistration = new PreRegistrationResponse();
                preRegistration.setNom(user.getFirstName());
                preRegistration.setPrenom(user.getLastName());
                preRegistration.setEmail(user.getEmail());
                return ResponseEntity.ok(preRegistration);
            } else {
                return ResponseEntity.status(400).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);
        }
    }


    @PostMapping("/token/")
    public ResponseEntity<?> authenticate(
            @RequestBody LoginRequest request,
            HttpServletResponse response) {
        try {
            User user = userRepository.findByEmail(request.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            String token = jwtService.generateToken(userDetails);
            String refreshToken = jwtService.generateToken(Map.of("tokenType", "refresh"), userDetails);

//            // Set JWT cookie
//            Cookie jwtCookie = new Cookie("jwt", token);
//            jwtCookie.setHttpOnly(true);
//            jwtCookie.setPath("/");
//            jwtCookie.setMaxAge(24 * 60 * 60); // 24 hours
//            response.addCookie(jwtCookie);
//
//            // Add Authorization header
//            response.setHeader("Authorization", "Bearer " + token);

            return ResponseEntity.ok()
                    .header("Authorization", "Bearer " + token)
                    .body(new LoginResponse(token, refreshToken));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }


    @PostMapping("/login_scolarite/")
    public ResponseEntity<?> loginScolarite(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

            if (userDetails instanceof CustomUserDetails) {
                CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
                if (!customUserDetails.isStaff()) {
                    return ResponseEntity.status(403)
                            .body("Access denied: User must be staff");
                }

                String accessToken = jwtService.generateToken(userDetails);
                String refreshToken = jwtService.generateToken(Map.of("tokenType", "refresh"), userDetails);
                return ResponseEntity.ok(new LoginResponse(accessToken, refreshToken));
            }
            return ResponseEntity.status(401)
                    .body("Unauthorized: User details not found");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie jwtCookie = new Cookie("jwt", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0);
        response.addCookie(jwtCookie);

        return ResponseEntity.ok().body("Logged out successfully");
    }

    private AuthResponse.UserInfo createUserInfo(User user) {
        List<String> groups = user.getUserGroup() != null ? user.getUserGroup().getGroups().stream()
                .map(AuthGroup::getName)
                .collect(Collectors.toList()) : Collections.emptyList();

        Map<String, Object> misc = new HashMap<>();

        Optional<Professeur> profOpt = professeurRepository.findByUser(user);
        if (profOpt.isPresent()) {
            return createProfessorInfo(profOpt.get());
        }

        return new AuthResponse.UserInfo(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                null,
                groups,
                misc);
    }

    private AuthResponse.UserInfo createProfessorInfo(Professeur professor) {
        User user = professor.getUser();
        List<String> groups = user.getUserGroup() != null ? user.getUserGroup().getGroups().stream()
                .map(AuthGroup::getName)
                .collect(Collectors.toList()) : Collections.emptyList();

        Map<String, Object> misc = new HashMap<>();
        misc.put("grade", professor.getGrade());
        misc.put("nombreProposer", professor.getNombreProposer());
        misc.put("nombreEncadre", professor.getNombreEncadrer());
        misc.put("etablissement", professor.getEtablissement().getIdEtablissement());
        misc.put("labo", professor.getLabo_id());

        return new AuthResponse.UserInfo(
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                professor.getPathPhoto(),
                groups,
                misc);
    }

    private AuthResponse.UserInfo createCandidatInfo(Candidat candidat) {
        User user = candidat.getUser();
        List<String> groups = user.getUserGroup() != null ? user.getUserGroup().getGroups().stream()
                .map(AuthGroup::getName)
                .collect(Collectors.toList()) : Collections.emptyList();

        Map<String, Object> misc = new HashMap<>();
        misc.put("cne", candidat.getCne());
        misc.put("cin", candidat.getCin());
        misc.put("ville", candidat.getVille());
        misc.put("pays", candidat.getPays() != null ? candidat.getPays().getId() : null);

        return new AuthResponse.UserInfo(
                user.getEmail(),
                user.getLastName(),
                user.getFirstName(),
                candidat.getPathPhoto(),
                groups,
                misc);
    }

    @PostMapping("/verify-is-prof")
    public ResponseEntity<?> verifyProfessor(@RequestBody Map<String, String> request) {
        try {
            String idTokenString = request.get("idToken");
            GoogleIdToken.Payload payload = verifyGoogleToken(idTokenString);
            String email = payload.getEmail();

            Professeur professor = professeurRepository.findByUserEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Not a professor"));

            UserDetails userDetails = userDetailsService.loadUserByUsername(email);
            String token = jwtService.generateToken(userDetails);
            String refreshToken = jwtService.generateToken(Map.of("tokenType", "refresh"), userDetails);

            return ResponseEntity.ok(new AuthResponse(token, refreshToken, createProfessorInfo(professor)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register/candidat/")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            Pays pays = paysRepository.findByNom(request.getPays())
                    .orElseGet(() -> {
                        Pays newPays = new Pays();
                        newPays.setNom(request.getPays());
                        return paysRepository.save(newPays);
                    });

            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setUsername(request.getNom() + " " + request.getPrenom());
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setIsActive(true);
            user.setDateJoined(new Date());

            userRepository.save(user);
            paysRepository.save(pays);

            Candidat candidat = new Candidat();
            candidat.setUser(user);
            candidat.setPays(pays);
            candidat.setTelCandidat(request.getTelCandidat());
            candidat.setCne(request.getCne());
            candidat.setCin(request.getCin());
            candidat.setNomCandidatAr(request.getNomCandidatAr());
            candidat.setPrenomCandidatAr(request.getPrenomCandidatAr());
            candidat.setAdresse(request.getAdresse());
            candidat.setSexe(request.getSexe());
            candidat.setVilleDeNaissance(request.getVilleDeNaissance());
            candidat.setVilleDeNaissanceAr(request.getVilleDeNaissanceAr());
            candidat.setVille(request.getVille());
            candidat.setDateDeNaissance(request.getDateDeNaissance());
            candidat.setTypeDeHandiCape(request.getTypeDeHandiCape());
            candidat.setSituationFamiliale(request.getSituationfamiliale());

            Candidat createdCandidat = candidatService.createCandidat(candidat);

            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            String token = jwtService.generateToken(userDetails);
            String refreshToken = jwtService.generateToken(Map.of("tokenType", "refresh"), userDetails);

            return ResponseEntity.ok(new AuthResponse(token, refreshToken, createCandidatInfo(createdCandidat)));

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body("Registration failed: " + e.getMessage());
        }
    }

    @GetMapping("/protected")
    public ResponseEntity<?> protectedRoute(HttpServletRequest request) {
        UserDetails userDetails = (UserDetails) request.getAttribute("user");
        User user = ((CustomUserDetails) userDetails).getUser();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get-user-info/")
    public ResponseEntity<?> getUserInfo(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = ((CustomUserDetails) userDetails).getUser();
        return ResponseEntity.ok(createUserInfo(user));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + e.getMessage());
    }
}