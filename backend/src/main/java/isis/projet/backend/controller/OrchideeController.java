package isis.projet.backend.controller;

import isis.projet.backend.entity.Orchidee;
import isis.projet.backend.service.OrchideeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orchidees")
@CrossOrigin(origins = "http://localhost:5173")
public class OrchideeController {

    @Autowired
    private final OrchideeService orchideeService;

    @PostMapping
    public Orchidee enregistrerOrchidee(@RequestBody Orchidee Orchidee) {
        return orchideeService.createOrchidee(Orchidee);
    }
}