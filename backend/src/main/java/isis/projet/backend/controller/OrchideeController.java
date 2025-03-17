package isis.projet.backend.controller;

import isis.projet.backend.dto.OrchideeDTO;
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

    @PostMapping
    public ResponseEntity<OrchideeDTO> createOrchidee(@RequestBody OrchideeDTO orchideeDTO) {
        OrchideeDTO createdOrchidee = orchideeService.createOrchidee(orchideeDTO);
        return ResponseEntity.ok(createdOrchidee);
    }

    @GetMapping
    public ResponseEntity<List<OrchideeDTO>> getAllOrchidees() {
        List<OrchideeDTO> orchidees = orchideeService.getAllOrchidees();
        return ResponseEntity.ok(orchidees);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrchideeDTO> updateOrchidee(@PathVariable Integer id, @RequestBody OrchideeDTO orchideeDTO) {
        OrchideeDTO updatedOrchidee = orchideeService.updateOrchidee(id, orchideeDTO);
        return ResponseEntity.ok(updatedOrchidee);
    }

}
