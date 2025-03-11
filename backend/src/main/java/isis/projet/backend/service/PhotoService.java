package isis.projet.backend.service;

import isis.projet.backend.dao.PhotoRepository;
import isis.projet.backend.dto.PhotoDTO;
import isis.projet.backend.entity.Photo;
import isis.projet.backend.entity.Orchidee;
import isis.projet.backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    // Méthode pour obtenir une photo par ID et la convertir en PhotoDTO
    public PhotoDTO getPhotoById(Integer id) {
        Photo photo = photoRepository.findById(id).orElseThrow(() -> new RuntimeException("Photo non trouvée"));

        // Conversion en PhotoDTO
        return convertToDTO(photo);
    }

    // Méthode pour obtenir toutes les photos et les convertir en liste de PhotoDTO
    public List<PhotoDTO> getAllPhotos() {
        List<Photo> photos = photoRepository.findAll();
        return photos.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    // Méthode pour convertir une Photo en PhotoDTO
    private PhotoDTO convertToDTO(Photo photo) {
        User auteur = photo.getAuteur();
        Orchidee specimen = photo.getSpecimen();

        // Conversion en DTO avec les informations nécessaires
        return new PhotoDTO(
                photo.getId(),
                photo.getUrl(),
                photo.getDatePriseVue(),
                auteur.getId(),          // ID de l'auteur
                auteur.getNom(),         // Nom de l'auteur (optionnel)
                specimen.getId(),       // ID du spécimen
                specimen.getNomScientifique()  // Nom scientifique du spécimen (optionnel)
        );
    }
}