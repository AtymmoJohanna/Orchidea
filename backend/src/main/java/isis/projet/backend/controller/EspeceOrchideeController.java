package isis.projet.backend.controller;

import isis.projet.backend.dto.EspeceOrchideeDTO;
import isis.projet.backend.entity.EspeceOrchidee;
import isis.projet.backend.service.EspeceOrchideeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/especeOrchidees")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class EspeceOrchideeController {

    private final EspeceOrchideeService especeOrchideeService;

    @GetMapping
    public ResponseEntity<List<EspeceOrchideeDTO>> getAllEspecesOrchidee() {
        List<EspeceOrchidee> especes = especeOrchideeService.getAllEspecesOrchidee();
        // Mapping manuel de chaque entité en DTO
        List<EspeceOrchideeDTO> dtoList = especes.stream()
                .map(entity -> {
                    EspeceOrchideeDTO dto = new EspeceOrchideeDTO();
                    dto.setCode(entity.getCode());
                    dto.setNomScientifique(entity.getNomScientifique());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }
}
