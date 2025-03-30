package isis.projet.backend.controller;

import isis.projet.backend.entity.*;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/enums")

public class EnumController {

    // Récupérer toutes les formes
    @GetMapping("/formes")
    public List<String> getFormes() {
        return Arrays.stream(Forme.values())
                .map(Forme::name)
                .collect(Collectors.toList());
    }

    // Récupérer toutes les couleurs
    @GetMapping("/couleurs")
    public List<String> getCouleurs() {
        return Arrays.stream(Couleur.values())
                .map(Couleur::name)
                .collect(Collectors.toList());
    }

    // Récupérer tous les motifs
    @GetMapping("/motifs")
    public List<String> getMotifs() {
        return Arrays.stream(Motif.values())
                .map(Motif::name)
                .collect(Collectors.toList());
    }

    // Récupérer tous les états d'inflorescence
    @GetMapping("/etats-inflorescence")
    public List<String> getEtatsInflorescence() {
        return Arrays.stream(EtatInflorescence.values())
                .map(EtatInflorescence::name)
                .collect(Collectors.toList());
    }

    // Récupérer tous les nombres d'individus
    @GetMapping("/nbre-individus")
    public List<String> getNbreIndividus() {
        return Arrays.stream(NbreIndividu.values())
                .map(NbreIndividu::name)
                .collect(Collectors.toList());
    }

    // Récupérer toutes les variabilités de taxon
    @GetMapping("/variabilites-taxons")
    public List<String> getVariabiliteTaxons() {
        return Arrays.stream(VariabiliteTaxon.values())
                .map(VariabiliteTaxon::name)
                .collect(Collectors.toList());
    }
}