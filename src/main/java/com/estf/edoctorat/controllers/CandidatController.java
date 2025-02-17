package com.estf.edoctorat.controllers;
import com.estf.edoctorat.config.CustomUserDetails;
import com.estf.edoctorat.dto.*;
import com.estf.edoctorat.models.Annexe;
import com.estf.edoctorat.models.User;
import com.estf.edoctorat.services.CandidatService;
import com.estf.edoctorat.services.DiplomeService;
import lombok.extern.slf4j.Slf4j;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class CandidatController {

    @Autowired
    private CandidatService candidatService;
    @Autowired
    private DiplomeService diplomeService;
    @Value("${app.upload.dir}")
    private String uploadDir;

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


    @GetMapping("/get-candidat-profile/{id}")
    public ResponseEntity<CandidatDTO> getCandidatProfile(@PathVariable Long id) {
        CandidatDTO candidatDTO =  candidatService.getCandidatById(id);
        return candidatDTO != null ? ResponseEntity.ok(candidatDTO) : ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/candidat-info/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
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
//            @RequestParam("adresseAr") String adresseAr,
            @RequestParam("sexe") String sexe,
            @RequestParam("villeDeNaissance") String villeDeNaissance,
            @RequestParam("villeDeNaissanceAr") String villeDeNaissanceAr,
            @RequestParam("ville") String ville,
            @RequestParam("dateDeNaissance") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateDeNaissance,
            @RequestParam("telCandidat") String telCandidat,
            @RequestParam("fonctionnaire") String fonctionnaireStr, // Accept as String
            @RequestPart(value = "pathPhoto", required = false) MultipartFile file
    ) {
        boolean fonctionnaire = "oui".equalsIgnoreCase(fonctionnaireStr);

        PaysDTO paysDTO = new PaysDTO();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = customUserDetails.getUser().getId();

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
//        candidatDTO.setAdresseAr(adresseAr);
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
            String newFilePath = saveFile(file, "candidats");
            candidatDTO.setPathPhoto(newFilePath);
        }else {
            // Retain the existing photo if no new file is uploaded
            candidatDTO.setPathPhoto(oldPhotoPath);
        }

        // Update candidat info
        CandidatDTO updatedCandidatDTO = candidatService.updateCandidatInfo(userId, candidatDTO);

        // Delete the old photo AFTER successful update
        if (file != null && !file.isEmpty() && oldPhotoPath != null) {
            deleteFile(oldPhotoPath, "candidats");
        }

        return ResponseEntity.ok(updatedCandidatDTO);
    }

    @PostMapping("/candidat-info")
    public ResponseEntity<CandidatDTO> createCandidat(@RequestBody CandidatDTO candidatDTO) {
        CandidatDTO createdCandidatDTO = candidatService.createCandidat(candidatDTO);
        return  ResponseEntity.status(HttpStatus.CREATED).body(createdCandidatDTO);
    }

    @PostMapping(value = "/candidat-parcours/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Result<DiplomeDTO>> addCandidatParcours(
            @RequestParam("intitule") String intitule,
            @RequestParam("type") String type,
            @RequestParam("dateCommission") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateCommission,
            @RequestParam("pays") String pays,
            @RequestParam("ville") String ville,
            @RequestParam("province") String province,
            @RequestParam("mention") String mention,
            @RequestParam("etablissement") String etablissement,
            @RequestParam("specialite") String specialite,
            @RequestParam("moyen_generale") Double moyenGenerale,
            @RequestPart(value = "diplomeFile", required = true) MultipartFile diplomeFile,
            @RequestPart(value = "releveFile", required = true) MultipartFile releveFile
    ) {
        // Get the user ID from the authentication
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = customUserDetails.getUser().getId();

        // Build the DiplomeDTO
        DiplomeDTO diplomeDTO = new DiplomeDTO();
        diplomeDTO.setIntitule(intitule);
        diplomeDTO.setType(type);
        diplomeDTO.setDateCommission(dateCommission);
        diplomeDTO.setPays(pays);
        diplomeDTO.setVille(ville);
        diplomeDTO.setProvince(province);
        diplomeDTO.setMention(mention);
        diplomeDTO.setEtablissement(etablissement);
        diplomeDTO.setSpecialite(specialite);
        diplomeDTO.setMoyen_generale(moyenGenerale);

        // Save the files and create annexes
        String diplomeFileUrl = saveFile(diplomeFile, "diplomes");
        String releveFileUrl = saveFile(releveFile, "releves");

        List<Annexe> annexes = new ArrayList<>();
        annexes.add(createAnnexe(diplomeFile, "DIPLOME", diplomeFileUrl));
        annexes.add(createAnnexe(releveFile, "RELEVE", releveFileUrl));

        // Call the service to save the Diplome
        Result<DiplomeDTO> savedDiplome = diplomeService.addDiplome(diplomeDTO, annexes, userId);

        return ResponseEntity.ok(savedDiplome);
    }


    @GetMapping("/candidat-parcours/")
    public ResponseEntity<Result<DiplomeDTO>> getInfoDiplomes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();
        Long userId = customUserDetails.getUser().getId();

        List<DiplomeDTO> diplomes = diplomeService.getDiplomesByCandidatId(userId);

        Result<DiplomeDTO> result = new Result<>();
        result.setCount(diplomes.size());
        result.setNext(null);
        result.setPrevious(null);
        result.setResults(diplomes);

        return ResponseEntity.ok(result);
    }


    private String saveFile(MultipartFile file, String subDirName) {
        try {
            // Create the candidats subdirectory
            String UploadDir = uploadDir + File.separator + subDirName;
            File dir = new File(UploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Generate filename
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String filePath = Paths.get(UploadDir, fileName).toString();

            // Save file
            file.transferTo(new File(filePath));

            // Return URL path with subdirectory
            return "/uploads/" + subDirName + "/" + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save file: " + e.getMessage(), e);
        }
    }

    private void deleteFile(String fileUrl, String subDir) {
        if (fileUrl == null || fileUrl.isEmpty()) {
            return;
        }

        String[] pathParts = fileUrl.split("/");
        String fileName = pathParts[pathParts.length - 1];

        String filePath = Paths.get(uploadDir, subDir, fileName).toString();

        try {
            Files.deleteIfExists(Paths.get(filePath));
        } catch (IOException e) {
            System.err.println("Failed to delete old file: " + filePath);
            e.printStackTrace();
        }
    }

    private Annexe createAnnexe(MultipartFile file, String type, String fileUrl) {
        Annexe annexe = new Annexe();
        annexe.setTypeAnnexe(type);
        annexe.setTitre(file.getOriginalFilename());
        annexe.setPathFile(fileUrl);
        return annexe;
    }
}