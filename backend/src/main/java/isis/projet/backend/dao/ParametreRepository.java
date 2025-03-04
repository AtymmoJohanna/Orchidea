package isis.projet.backend.dao;

import isis.projet.backend.entity.Parametre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParametreRepository extends JpaRepository<Parametre, Integer> {
}