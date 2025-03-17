package isis.projet.backend.controller;

import isis.projet.backend.dto.AvisDTO;
import isis.projet.backend.entity.Avis;
import isis.projet.backend.service.AvisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/avis")
@RequiredArgsConstructor
public class AvisController {

    private final AvisService avisService;

    @PostMapping
    public ResponseEntity<AvisDTO> createAvis(@RequestBody AvisDTO avisDTO) {
        AvisDTO createdAvis = avisService.createAvis(avisDTO);
        return ResponseEntity.ok(createdAvis);
    }

    @GetMapping
    public ResponseEntity<List<AvisDTO>> getAllAvis() {
        List<AvisDTO> avisList = avisService.getAllAvis();
        return ResponseEntity.ok(avisList);
    }
}
