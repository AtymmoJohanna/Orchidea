package isis.projet.backend.controller;

import isis.projet.backend.dto.EspeceOrchideeDTO;
import isis.projet.backend.service.EspeceOrchideeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/especes-orchidees")
@RequiredArgsConstructor
public class EspeceOrchideeController {

    private final EspeceOrchideeService especeOrchideeService;

    @PostMapping
    public ResponseEntity<EspeceOrchideeDTO> createEspeceOrchidee(@RequestBody EspeceOrchideeDTO especeOrchideeDTO) {
        EspeceOrchideeDTO createdEspeceOrchidee = especeOrchideeService.createEspeceOrchidee(especeOrchideeDTO);
        return ResponseEntity.ok(createdEspeceOrchidee);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspeceOrchideeDTO> getEspeceOrchidee(@PathVariable Integer id) {
        EspeceOrchideeDTO especeOrchidee = especeOrchideeService.getEspeceOrchideeById(id);
        return especeOrchidee != null ? ResponseEntity.ok(especeOrchidee) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<EspeceOrchideeDTO>> getAllEspecesOrchidees() {
        List<EspeceOrchideeDTO> especes = especeOrchideeService.getAllEspecesOrchidees();
        return ResponseEntity.ok(especes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EspeceOrchideeDTO> updateEspeceOrchidee(@PathVariable Integer id, @RequestBody EspeceOrchideeDTO especeOrchideeDTO) {
        EspeceOrchideeDTO updatedEspeceOrchidee = especeOrchideeService.updateEspeceOrchidee(id, especeOrchideeDTO);
        return ResponseEntity.ok(updatedEspeceOrchidee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEspeceOrchidee(@PathVariable Integer id) {
        especeOrchideeService.deleteEspeceOrchidee(id);
        return ResponseEntity.noContent().build();
    }
}
