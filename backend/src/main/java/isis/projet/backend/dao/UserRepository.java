package isis.projet.backend.dao;

import isis.projet.backend.entity.Avis;
import isis.projet.backend.entity.EspeceOrchidee;
import isis.projet.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Recherche un user par son mail (unique)
     * @param mail le mail recherché
     * @return Un user avec mail
     */
    User findByMail(String mail);

    /**
     * Recherche un user par son pwd (unique)
     * @param pwd le pwd recherché
     * @return Un user avec pwd
     */
    User findByPwd(String pwd);
}

