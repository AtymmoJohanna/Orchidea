package isis.projet.backend.service;

import isis.projet.backend.dao.OrchideeRepository;
import isis.projet.backend.dao.PhotoRepository;
import isis.projet.backend.dao.UtilisateurRepository;
import isis.projet.backend.dto.PhotoDTO;
import isis.projet.backend.entity.Photo;
import isis.projet.backend.entity.Orchidee;
import isis.projet.backend.entity.Utilisateur;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhotoService {
    private final PhotoRepository photoRepository;
    private final OrchideeRepository orchideeRepository;

    @Transactional
    public Photo createPhoto(Photo photo) {
        return photoRepository.save(photo);
    }
}