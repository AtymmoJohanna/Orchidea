package isis.projet.backend.controller;

import isis.projet.backend.dto.AvisDTO;
import isis.projet.backend.service.AvisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // autoriser les requêtes du frontend
public class AvisController {

    private final AvisService avisService;

    @PostMapping
    public ResponseEntity<AvisDTO> createAvis(@RequestBody AvisDTO avisDTO) {
        AvisDTO saved = avisService.createAvis(avisDTO);
        return ResponseEntity.ok(saved);
    }

    @GetMapping
    public List<AvisDTO> getAllAvis() {
        return avisService.getAllAvis();
    }
}
