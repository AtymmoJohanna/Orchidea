package isis.projet.backend.service;

import isis.projet.backend.dao.EspeceOrchideeRepository;
import isis.projet.backend.entity.EspeceOrchidee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EspeceOrchideeService {

    private final EspeceOrchideeRepository especeOrchideeRepository;

    // Méthode pour créer une nouvelle espèce d'orchidée
    public EspeceOrchidee createEspeceOrchidee(EspeceOrchidee especeOrchidee) {
        return especeOrchideeRepository.save(especeOrchidee);
    }

    public EspeceOrchidee getEspeceByNomScientifique(String nomScientifique) {
        return especeOrchideeRepository.findByNomScientifique(nomScientifique);
    }

    public List<EspeceOrchidee> getAllEspecesOrchidee() {
        return especeOrchideeRepository.findAll();
    }

}
