package isis.projet.backend.controller;

import isis.projet.backend.dto.OrchideeDTO;
import isis.projet.backend.entity.Orchidee;
import isis.projet.backend.service.OrchideeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/orchidees")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class OrchideeController {

    private final OrchideeService orchideeService;

    @PostMapping
    public ResponseEntity<OrchideeDTO> enregistrerOrchidee(@RequestBody Orchidee orchidee) {
        // Création de l'orchidée via le service
        Orchidee orchideeCreee = orchideeService.createOrchidee(orchidee);
        // Conversion de l'entité en DTO
        OrchideeDTO dto = mapToDTO(orchideeCreee);
        return ResponseEntity.ok(dto);
    }

    @GetMapping
    public ResponseEntity<List<OrchideeDTO>> getAllOrchidees() {
        List<Orchidee> orchidees = orchideeService.getAll();
        List<OrchideeDTO> dtoList = orchidees.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    // Méthode privée de mapping manuel de l'entité Orchidee vers OrchideeDTO
    private OrchideeDTO mapToDTO(Orchidee entity) {
        OrchideeDTO dto = new OrchideeDTO();
        dto.setId(entity.getId());
        dto.setCommentaire(entity.getCommentaire());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setDateEnreg(entity.getDateEnreg());
        dto.setEtat(entity.getEtat());
        dto.setCouleur(entity.getCouleur());
        dto.setVarTaxon(entity.getVarTaxon());
        dto.setNbreIndividu(entity.getNbreIndividu());
        dto.setMotif(entity.getMotif());
        dto.setForme(entity.getForme());

        // Utilisation de getEspece() puisque le champ se nomme "espece" dans l'entité
        if (entity.getEspece() != null) {
            dto.setEspeceCode(entity.getEspece().getCode());
        }

        if (entity.getAuteur() != null) {
            dto.setAuteurId(entity.getAuteur().getId());
        }
        return dto;
    }

}
