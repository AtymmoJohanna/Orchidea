package isis.projet.backend.dao;


import java.util.List;

import isis.projet.backend.entity.Avis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AvisRepository extends JpaRepository<Avis, Integer> {
    /**
     * Trouve les avis à partir du nom de l'user
     * version JPQL
     * @param nomUser le nomUser à chercher
     * @return les avis de cet user
     */
    @Query("""
		SELECT a
		FROM Avis a
		WHERE a.emetteur.nom = :nomUser""")
    List<Avis> avisPourUserJPQL(String nomUser);


    @Query("select a from Avis a")
    List<AvisProjection> findAllWithProjection();

}
