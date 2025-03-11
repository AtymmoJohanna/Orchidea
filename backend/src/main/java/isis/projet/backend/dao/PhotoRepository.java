package isis.projet.backend.dao;

import isis.projet.backend.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    // Recherche une photo par son URL
    List<Photo> findByUrl(String url);

    // Recherche toutes les photos par l'orchidée associée
    List<Photo> findBySpecimen_Id(Integer orchideeId);

    // Recherche toutes les photos par l'utilisateur auteur
    List<Photo> findByAuteur_Id(Integer userId);
}
