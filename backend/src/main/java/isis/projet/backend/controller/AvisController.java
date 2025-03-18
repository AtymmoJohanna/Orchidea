package isis.projet.backend.controller;

import isis.projet.backend.dao.UtilisateurRepository;
import isis.projet.backend.dto.AvisDTO;
import isis.projet.backend.entity.Avis;
import isis.projet.backend.entity.Utilisateur;
import isis.projet.backend.service.AvisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/avis")
@RequiredArgsConstructor
public class AvisController {
    private final AvisService avisService;
    private final UtilisateurRepository utilisateurRepository;

    @PostMapping
    public ResponseEntity<AvisDTO> createAvis(@RequestBody AvisDTO avisDTO) {
        Utilisateur emetteur = utilisateurRepository.findById(avisDTO.getEmetteurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Avis avis = new Avis(avisDTO.getCommentaire(), emetteur);
        Avis savedAvis = avisService.createAvis(avis);

        return ResponseEntity.ok(new AvisDTO(savedAvis));  // ✅ La conversion se fait ici
    }
}
