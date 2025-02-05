package com.estf.edoctorat.controllers;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.*;
import java.util.stream.Collectors;

import com.estf.edoctorat.config.CustomUserDetails;
import com.estf.edoctorat.config.GoogleTokenVerifier;
import com.estf.edoctorat.dtos.auth.*;
import com.estf.edoctorat.models.*;
import com.estf.edoctorat.repositories.*;
import com.estf.edoctorat.services.CandidatService;
import com.estf.edoctorat.services.EmailService;
import com.estf.edoctorat.services.JwtService;
import com.estf.edoctorat.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
@Slf4j
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
    @Autowired
    AuthGroupRepository authGroupRepository;
    @Autowired
    UserGroupRepository userGroupRepository;

    private GoogleIdToken.Payload verifyGoogleToken(String idTokenString) throws GeneralSecurityException, IOException {
        return googleTokenVerifier.verify(idTokenString);
    }

    @PostMapping("/confirm-email/")
    public ResponseEntity<Map<String, String>> confirmEmail(@RequestBody EmailConfirmationRequest request) {
        if (userService.emailExists(request.getEmail())) {
            log.error("ERROR => EMAIL ALREADY IN USE ");
            return ResponseEntity.badRequest().body(Map.of("error", "Email already in use"));
        }

        User user = userService.createUser(request.getEmail(), request.getNom(), request.getPrenom());
        String token = jwtService.generateToken(new CustomUserDetails(user,null));

        String confirmationUrl = request.getOrigin() + "?token=" + token;

        emailService.sendConfirmationEmail(user.getEmail(), confirmationUrl);
        log.error("SUCCESS => CONFIRMATION EMAIL SENT ");
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
    public ResponseEntity<?> authenticate(@RequestBody LoginRequest request) {
        try {
            User user = userRepository.findByEmail(request.getUsername())
                    .orElseThrow(() -> {
                        log.warn("Authentication failed: User not found - {}", request.getUsername());
                        return new UsernameNotFoundException("User not found");
                    });
            try {
                Authentication authentication = authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
                );
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (BadCredentialsException e) {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body(new ErrorResponse(
                                "Invalid credentials",
                                "AUTH_001",
                                System.currentTimeMillis()
                        ));
            }

            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
            String accessToken = jwtService.generateToken(userDetails);
            String refreshToken = jwtService.generateToken(
                    Map.of("tokenType", "refresh"),
                    userDetails
            );

            return ResponseEntity.ok()
                    .body(new LoginResponse(accessToken, refreshToken));

        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse(
                            "An error occurred during authentication",
                            "AUTH_500",
                            System.currentTimeMillis()
                    ));
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
        List<String> groups = user.getUserGroups() != null
                ? user.getUserGroups().stream()
                .map(userAuthGroup -> userAuthGroup.getAuthGroup().getName())
                .collect(Collectors.toList())
                : Collections.emptyList();

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
        List<String> groups = user.getUserGroups() != null
                ? user.getUserGroups().stream()
                .map(userAuthGroup -> userAuthGroup.getAuthGroup().getName())
                .collect(Collectors.toList())
                : Collections.emptyList();

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
        List<String> groups = user.getUserGroups() != null
                ? user.getUserGroups().stream()
                .map(userAuthGroup -> userAuthGroup.getAuthGroup().getName())
                .collect(Collectors.toList())
                : Collections.emptyList();

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

            List<String> groupNames = List.of("candidat");
            if (user.getUserGroups() == null) {
                user.setUserGroups(new ArrayList<>());
            }

            for (String groupName : groupNames) {
                AuthGroup authGroup = authGroupRepository.findByName(groupName)
                        .orElseGet(() -> {
                            AuthGroup newGroup = new AuthGroup();
                            newGroup.setName(groupName);
                            return authGroupRepository.save(newGroup);
                        });

                // Check if the user already has the group to avoid duplicates
                boolean alreadyAssigned = user.getUserGroups().stream()
                        .anyMatch(ug -> ug.getAuthGroup().getName().equals(groupName));

                if (!alreadyAssigned) {
                    UserGroup userGroup = new UserGroup();
                    userGroup.setUser(user);
                    userGroup.setAuthGroup(authGroup);
                    user.getUserGroups().add(userGroup);
                }
            }

            userRepository.save(user);

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