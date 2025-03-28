package isis.projet.backend.controller;

import isis.projet.backend.dto.EspeceOrchideeDTO;
import isis.projet.backend.entity.EspeceOrchidee;
import isis.projet.backend.service.EspeceOrchideeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/especes-orchidees")
@RequiredArgsConstructor
public class EspeceOrchideeController {

    private final EspeceOrchideeService especeOrchideeService;

    // Méthode POST pour créer une nouvelle espèce d'orchidée
    @PostMapping
    public ResponseEntity<EspeceOrchideeDTO> createEspeceOrchidee(@RequestBody EspeceOrchideeDTO especeDTO) {
        // Convertir le DTO en Entité
        EspeceOrchidee espece = new EspeceOrchidee();
        espece.setNomScientifique(especeDTO.getNomScientifique());

        // Sauvegarder l'entité via le service
        EspeceOrchidee savedEspece = especeOrchideeService.createEspeceOrchidee(espece);

        // Convertir l'entité en DTO pour la réponse
        EspeceOrchideeDTO savedEspeceDTO = new EspeceOrchideeDTO();
        savedEspeceDTO.setCode(savedEspece.getCode());
        savedEspeceDTO.setNomScientifique(savedEspece.getNomScientifique());

        // Retourner la réponse avec le DTO
        return ResponseEntity.ok(savedEspeceDTO);
    }
}