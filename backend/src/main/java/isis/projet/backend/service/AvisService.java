package isis.projet.backend.service;

import isis.projet.backend.dao.AvisRepository;
import isis.projet.backend.dao.UtilisateurRepository;
import isis.projet.backend.dto.AvisDTO;
import isis.projet.backend.entity.Avis;
import isis.projet.backend.entity.Utilisateur;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class AvisService {


    private final AvisRepository avisRepository;

    private final UtilisateurRepository utilisateurRepository;

    public AvisDTO createAvis(AvisDTO dto) {
        Utilisateur emetteur = utilisateurRepository.findById(dto.getEmetteurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Avis avis = new Avis();
        avis.setCommentaire(dto.getCommentaire());
        avis.setDate(LocalDateTime.now());
        avis.setEmetteur(emetteur);

        Avis savedAvis = avisRepository.save(avis);
        return new AvisDTO(savedAvis);
    }

    public List<AvisDTO> getAllAvis(){
        return avisRepository.findAll()
                .stream()
                .map(AvisDTO::new)
                .collect(Collectors.toList());
    }
}
