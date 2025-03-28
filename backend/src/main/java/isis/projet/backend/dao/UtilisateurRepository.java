package isis.projet.backend.dao;

import isis.projet.backend.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    Optional<Utilisateur> findByMail(String mail); // 🔍 Recherche un utilisateur par email
}
