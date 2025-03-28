package isis.projet.backend.service;

import isis.projet.backend.dao.AvisRepository;
import isis.projet.backend.dao.UtilisateurRepository;
import isis.projet.backend.entity.Avis;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AvisService {
    private final AvisRepository avisRepository;
    private final UtilisateurRepository utilisateurRepository;

    @Transactional
    public Avis createAvis(Avis avis) {
        return avisRepository.save(avis);
    }
}