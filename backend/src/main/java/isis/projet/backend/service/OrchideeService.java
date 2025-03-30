package isis.projet.backend.service;

import isis.projet.backend.dao.EspeceOrchideeRepository;
import isis.projet.backend.entity.Orchidee;
import isis.projet.backend.dao.OrchideeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrchideeService {
    private final OrchideeRepository orchideeRepository;

    @Transactional
    public Orchidee createOrchidee(Orchidee orchidee) {
        return orchideeRepository.save(orchidee);
    }
}