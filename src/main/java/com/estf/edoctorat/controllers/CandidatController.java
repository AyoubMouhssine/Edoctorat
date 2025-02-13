package com.estf.edoctorat.controllers;
import com.estf.edoctorat.config.CustomUserDetails;
import com.estf.edoctorat.dto.CandidatDTO;
import com.estf.edoctorat.dto.PaysDTO;
import com.estf.edoctorat.services.CandidatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@RestController
@RequestMapping("/api")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;


    /*
    @GetMapping("/candidat-info/")
    public ResponseEntity<CandidatDTO> getCandidatInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        Long userId = customUserDetails.getUser().getId();

        CandidatDTO candidatDTO = candidatService.getCandidatByUserId(userId);

        if (candidatDTO != null) {
            return ResponseEntity.ok(candidatDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }*/


    @GetMapping("/get-candidat-profile/{id}")
    public ResponseEntity<CandidatDTO> getCandidatProfile(@PathVariable Long id) {
        CandidatDTO candidatDTO =  candidatService.getCandidatById(id);
        return candidatDTO != null ? ResponseEntity.ok(candidatDTO) : ResponseEntity.notFound().build();
    }


    /*@PutMapping("/candidat-info")
    public ResponseEntity<CandidatDTO> updateCandidatInfo(@RequestParam Long id, @RequestBody CandidatDTO candidatDTO) {
        CandidatDTO updatedCandidatDTO = candidatService.updateCandidat(id,candidatDTO);
        return updatedCandidatDTO != null ? ResponseEntity.ok(updatedCandidatDTO) : ResponseEntity.notFound().build();
    }*/

    @PostMapping("/candidat-info")
    public ResponseEntity<CandidatDTO> createCandidat(@RequestBody CandidatDTO candidatDTO) {
        CandidatDTO createdCandidatDTO = candidatService.createCandidat(candidatDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdCandidatDTO);
    }

    @GetMapping("/candidat-info/")
    public ResponseEntity<CandidatDTO> getCandidatInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = customUserDetails.getUser().getId();
        CandidatDTO candidatDTO = candidatService.getCandidatByUserId(userId);

        if (candidatDTO != null) {
            return ResponseEntity.ok(candidatDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping(value = "/candidat-info", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<CandidatDTO> updateCandidatInfo(
            // User-related fields
            @RequestParam("prenom") String prenom,
            @RequestParam("nom") String nom,
            @RequestParam("mailCandidat") String mailCandidat,

            // Candidat fields
            @RequestParam("nomCandidatAr") String nomCandidatAr,
            @RequestParam("prenomCandidatAr") String prenomCandidatAr,
            @RequestParam("cin") String cin,
            @RequestParam("cne") String cne,
            @RequestParam("adresse") String adresse,
            @RequestParam("adresseAr") String adresseAr,
            @RequestParam("sexe") String sexe,
            @RequestParam("villeDeNaissance") String villeDeNaissance,
            @RequestParam("villeDeNaissanceAr") String villeDeNaissanceAr,
            @RequestParam("ville") String ville,
            @RequestParam("dateDeNaissance") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateDeNaissance,
            @RequestParam("telCandidat") String telCandidat,
            @RequestParam("fonctionnaire") String fonctionnaireStr, // Accept as String
            @RequestPart(value = "pathPhoto", required = false) MultipartFile file
    ) {
        // Convert "oui" → true, "non" → false
        boolean fonctionnaire = "oui".equalsIgnoreCase(fonctionnaireStr);

        // Create PaysDTO (if needed)
        PaysDTO paysDTO = new PaysDTO();
        // paysDTO.setId(paysId); // Keep commented
        // paysDTO.setNom(paysNom); // Keep commented

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = customUserDetails.getUser().getId();

        // Fetch the existing candidate to get the old photo path
        CandidatDTO existingCandidat = candidatService.getCandidatByUserId(userId);
        String oldPhotoPath = existingCandidat != null ? existingCandidat.getPathPhoto() : null;

        // Create CandidatDTO
        CandidatDTO candidatDTO = new CandidatDTO();
        candidatDTO.setPrenom(prenom);
        candidatDTO.setNom(nom);
        candidatDTO.setEmail(mailCandidat);
        candidatDTO.setNomCandidatAr(nomCandidatAr);
        candidatDTO.setPrenomCandidatAr(prenomCandidatAr);
        candidatDTO.setCin(cin);
        candidatDTO.setCne(cne);
        candidatDTO.setAdresse(adresse);
        candidatDTO.setAdresseAr(adresseAr);
        candidatDTO.setPays(paysDTO); // Set PaysDTO (even if fields are commented)
        candidatDTO.setSexe(sexe);
        candidatDTO.setVilleDeNaissance(villeDeNaissance);
        candidatDTO.setVilleDeNaissanceAr(villeDeNaissanceAr);
        candidatDTO.setVille(ville);
        candidatDTO.setDateDeNaissance(dateDeNaissance);
        // candidatDTO.setTypeDeHandiCape(typeDeHandiCape); // Keep commented
        // candidatDTO.setAcademie(academie); // Keep commented
        candidatDTO.setTelCandidat(telCandidat);
        // candidatDTO.setSituationFamiliale(situationFamiliale); // Keep commented
        candidatDTO.setFonctionnaire(fonctionnaire); // Set the boolean value

        // Handle file upload
        if (file != null && !file.isEmpty()) {
            // Save the new file
            String newFilePath = saveFile(file);
            candidatDTO.setPathPhoto(newFilePath);
        }else {
            // Retain the existing photo if no new file is uploaded
            candidatDTO.setPathPhoto(oldPhotoPath);
        }

        // Update candidat info
        CandidatDTO updatedCandidatDTO = candidatService.updateCandidatInfo(userId, candidatDTO);

        // Delete the old photo AFTER successful update
        if (file != null && !file.isEmpty() && oldPhotoPath != null) {
            deleteFile(oldPhotoPath);
        }

        return ResponseEntity.ok(updatedCandidatDTO);
    }

    @Value("${app.upload.dir}")
    private String uploadDir;

    private String saveFile(MultipartFile file) {
        try {
            // Create the candidats subdirectory
            String candidateUploadDir = uploadDir + File.separator + "candidats";
            File dir = new File(candidateUploadDir);
            if (!dir.exists()) {
                dir.mkdirs();  // Create directories if missing
            }

            // Generate filename
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = Paths.get(candidateUploadDir, fileName).toString();

            // Save file
            file.transferTo(new File(filePath));

            // Return URL path with subdirectory
            return "/uploads/candidats/" + fileName;  // URL path for access
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + e.getMessage(), e);
        }
    }

    private void deleteFile(String fileUrl) {
        if (fileUrl == null || fileUrl.isEmpty()) {
            return;
        }

        // Extract the filename from the URL (e.g., "/uploads/candidats/photo.png" → "photo.png")
        String[] pathParts = fileUrl.split("/");
        String fileName = pathParts[pathParts.length - 1];

        // Build the full filesystem path
        String filePath = Paths.get(uploadDir, "candidats", fileName).toString();

        // Delete the file
        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Failed to delete old file: " + filePath);
            e.printStackTrace();
        }
    }
}