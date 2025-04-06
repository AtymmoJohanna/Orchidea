package isis.projet.backend.controller;

import isis.projet.backend.entity.Utilisateur;
import isis.projet.backend.dao.UtilisateurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;


import java.util.Optional;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173") // 🔥 Autorise les requêtes du frontend (ajuste si nécessaire)
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;

    @PostMapping("/inscription")
    public ResponseEntity<?> inscrireUtilisateur(@RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> utilisateurExistant = utilisateurRepository.findByMail(utilisateur.getMail());

        if (utilisateurExistant.isPresent()) {
            return ResponseEntity.badRequest().body("Cet email est déjà utilisé !");
        }

        Utilisateur nouvelUtilisateur = new Utilisateur(
                utilisateur.getMail(),
                utilisateur.getPwd(),
                utilisateur.getNom(),
                utilisateur.getPrenom()  // Ajout du prénom
        );

        utilisateurRepository.save(nouvelUtilisateur);
        return ResponseEntity.ok("Utilisateur inscrit avec succès !");
    }
    @PostMapping("/connexion")
    public ResponseEntity<?> connexionUtilisateur(@RequestBody Utilisateur utilisateur) {
        Optional<Utilisateur> utilisateurExistant = utilisateurRepository.findByMail(utilisateur.getMail());

        if (utilisateurExistant.isPresent()) {
            Utilisateur u = utilisateurExistant.get();

            if (u.getPwd().equals(utilisateur.getPwd())) {
                return ResponseEntity.ok(u); // ✅ Connexion réussie, renvoie les infos utilisateur
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Mot de passe incorrect !");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé !");
        }
    }

}

