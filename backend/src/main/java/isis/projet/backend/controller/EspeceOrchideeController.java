package isis.projet.backend.controller;

import isis.projet.backend.dto.EspeceOrchideeDTO;
import isis.projet.backend.entity.EspeceOrchidee;
import isis.projet.backend.service.EspeceOrchideeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especeOrchidees")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class EspeceOrchideeController {

    private final EspeceOrchideeService especeOrchideeService;

    @GetMapping
    public ResponseEntity<List<EspeceOrchidee>> getAllEspecesOrchidee() {
        List<EspeceOrchidee> especes = especeOrchideeService.getAllEspecesOrchidee();

        return ResponseEntity.ok(especes);
    }

}