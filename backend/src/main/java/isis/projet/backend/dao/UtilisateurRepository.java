package isis.projet.backend.dao;

//import isis.projet.backend.entity.Avis;
//import isis.projet.backend.entity.EspeceOrchidee;
import isis.projet.backend.entity.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {
    /**
     * Recherche un user par son mail (unique)
     * @param mail le mail recherché
     * @return Un user avec mail
     */
    Optional<Utilisateur> findByMail(String mail);

    /**
     * Recherche un user par son pwd (unique)
     * @param pwd le pwd recherché
     * @return Un user avec pwd
     */
    Utilisateur findByPwd(String pwd);
}

