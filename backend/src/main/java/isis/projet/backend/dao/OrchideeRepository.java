package isis.projet.backend.dao;

import isis.projet.backend.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrchideeRepository extends JpaRepository<Orchidee, Integer> {
    /**
     * Trouve les orchidees par leur état d'inflorescence
     * @param etat l'état d'inflorescence recherché
     * @return la liste des orchidees correspondant à cet état
     */
    List<Orchidee> findByEtat(EtatInflorescence etat);

    /**
     * Trouve les orchidees par leur couleur
     * @param couleur la couleur recherchée
     * @return la liste des orchidees ayant cette couleur
     */
    List<Orchidee> findByCouleurIn(List<Couleur> couleur);

    /**
     * Trouve les orchidees en fonction de leur espèce
     * @param espece l'espèce recherchée
     * @return la liste des orchidees appartenant à cette espèce
     */
    List<Orchidee> findByEspece(EspeceOrchidee espece);

    /**
     * Trouve toutes les orchidees créées après une certaine date
     * @param date la date à partir de laquelle chercher
     * @return une liste d'orchidees créées après cette date
     */
    List<Orchidee> findByDateEnregAfter(LocalDate date);

    /**
     * Trouve les orchidees associées à un utilisateur spécifique (par auteur)
     * @param auteur l'utilisateur (auteur) des orchidees
     * @return la liste des orchidees créées par cet auteur
     */
    List<Orchidee> findByAuteur(Utilisateur auteur);

}
