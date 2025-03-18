package isis.projet.backend.controller;

import isis.projet.backend.dao.EspeceOrchideeRepository;
import isis.projet.backend.dao.UtilisateurRepository;
import isis.projet.backend.dto.OrchideeDTO;
import isis.projet.backend.entity.EspeceOrchidee;
import isis.projet.backend.entity.Orchidee;
import isis.projet.backend.entity.Utilisateur;
import isis.projet.backend.service.OrchideeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/orchidees")
@RequiredArgsConstructor
public class OrchideeController {

    private final OrchideeService orchideeService;
    private final EspeceOrchideeRepository especeOrchideeRepository;
    private final UtilisateurRepository utilisateurRepository; // Si tu as besoin de l'auteur

    @PostMapping
    public ResponseEntity<OrchideeDTO> createOrchidee(@RequestBody OrchideeDTO orchideeDTO) {

        // Récupérer l'espèce à partir du code
        EspeceOrchidee espece = especeOrchideeRepository.findById(orchideeDTO.getEspeceCode())
                .orElseThrow(() -> new RuntimeException("Espèce non trouvée"));

        // Récupérer l'auteur à partir de son ID (si nécessaire)
        Utilisateur auteur = utilisateurRepository.findById(orchideeDTO.getAuteurId())
                .orElseThrow(() -> new RuntimeException("Auteur non trouvé"));

        // Créer l'entité Orchidee à partir du DTO
        Orchidee orchidee = new Orchidee(
                orchideeDTO.getCommentaire(),
                orchideeDTO.getLatitude(),
                orchideeDTO.getLongitude(),
                espece,
                auteur
        );

        // Ajouter les autres propriétés
        orchidee.setEtat(orchideeDTO.getEtat());
        orchidee.setCouleur(orchideeDTO.getCouleur());
        orchidee.setVarTaxon(orchideeDTO.getVarTaxon());
        orchidee.setNbreIndividu(orchideeDTO.getNbreIndividu());
        orchidee.setMotif(orchideeDTO.getMotif());
        orchidee.setForme(orchideeDTO.getForme());

        // Sauvegarder l'orchidée via le service
        Orchidee savedOrchidee = orchideeService.createOrchidee(orchidee);

        // Conversion de l'entité Orchidee en DTO
        OrchideeDTO savedOrchideeDTO = new OrchideeDTO();
        savedOrchideeDTO.setId(savedOrchidee.getId());
        savedOrchideeDTO.setCommentaire(savedOrchidee.getCommentaire());
        savedOrchideeDTO.setLatitude(savedOrchidee.getLatitude());
        savedOrchideeDTO.setLongitude(savedOrchidee.getLongitude());
        savedOrchideeDTO.setDateEnreg(savedOrchidee.getDateEnreg());
        savedOrchideeDTO.setEtat(savedOrchidee.getEtat());
        savedOrchideeDTO.setCouleur(savedOrchidee.getCouleur());
        savedOrchideeDTO.setVarTaxon(savedOrchidee.getVarTaxon());
        savedOrchideeDTO.setNbreIndividu(savedOrchidee.getNbreIndividu());
        savedOrchideeDTO.setMotif(savedOrchidee.getMotif());
        savedOrchideeDTO.setForme(savedOrchidee.getForme());
        savedOrchideeDTO.setEspeceCode(savedOrchidee.getEspece().getCode());
        savedOrchideeDTO.setAuteurId(savedOrchidee.getAuteur().getId());

        // Retourner le DTO de l'orchidée créée
        return ResponseEntity.ok(savedOrchideeDTO);
    }
}