package isis.projet.backend.dao;

import isis.projet.backend.entity.Avis;
import org.springframework.data.jpa.repository.JpaRepository;

// Interface pour effectuer les opérations CRUD basiques sur les avis
public interface AvisRepository extends JpaRepository<Avis, Integer> {

}