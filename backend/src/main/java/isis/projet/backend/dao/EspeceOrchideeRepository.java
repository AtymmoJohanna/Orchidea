import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspeceOrchideeRepository  extends JpaRepository<EspeceOrchidee, Integer> {
    /**
     * Recherche une Espece Orchidee par son nom Scientifique (unique)
     * @param nomScientifique le nom Scientifique recherché
     * @return Une Espece Orchidee avec ce nom Scientifique
     */
    EspeceOrchidee findByNomScientifique(String nomScientifique);
    /**
     * Recherche les EspeceOrchidee dont le nomScientifique contient une sous-chaine
     * @param substring la sous-chaine à rechercher dans le nomScientifique
     * @return la liste des EspeceOrchidee dont le nomScientifique contient substring
     */
    List<EspeceOrchidee> findByNomScientifiqueContaining(String substring);
}