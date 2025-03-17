package isis.projet.backend.service;

import isis.projet.backend.dto.AvisDTO;
import isis.projet.backend.entity.Avis;
import isis.projet.backend.entity.User;
import isis.projet.backend.dao.AvisRepository;
import isis.projet.backend.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AvisService {

    private final AvisRepository avisRepository;
    private final UserRepository userRepository;

    // Créer un avis
    @Transactional
    public AvisDTO createAvis(AvisDTO avisDTO) {
        // Chercher l'utilisateur par ID
        User emetteur = userRepository.findById(avisDTO.getEmetteurId()).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Créer l'entité Avis à partir du DTO
        Avis avis = new Avis(avisDTO.getCommentaire(), emetteur);
        // Sauvegarder l'avis dans la base de données
        Avis savedAvis = avisRepository.save(avis);

        // Retourner le DTO de l'avis sauvegardé
        return new AvisDTO(savedAvis);
    }

    // Obtenir tous les avis
    public List<AvisDTO> getAllAvis() {
        List<Avis> avisList = avisRepository.findAll();
        return avisList.stream().map(avis -> new AvisDTO(avis)).collect(Collectors.toList());
    }
}