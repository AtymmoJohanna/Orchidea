package isis.projet.backend.service;

import isis.projet.backend.dao.OrchideeRepository;
import isis.projet.backend.dao.PhotoRepository;
import isis.projet.backend.dao.UserRepository;
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

    @Autowired
    private UserRepository userRepository;  // Assurez-vous que ce repository existe

    @Autowired
    private OrchideeRepository orchideeRepository;  // Assurez-vous que ce repository existe

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
                specimen.getId()       // ID du spécimen
        );
    }

    public PhotoDTO createPhoto(PhotoDTO photoDTO) {
        User auteur = userRepository.findById(photoDTO.getAuteurId())
                .orElseThrow(() -> new RuntimeException("Auteur non trouvé"));

        Orchidee specimen = orchideeRepository.findById(photoDTO.getSpecimenId())
                .orElseThrow(() -> new RuntimeException("Spécimen non trouvé"));

        Photo photo = new Photo(photoDTO.getUrl(), auteur, specimen);
        photo.setDatePriseVue(photoDTO.getDatePriseVue());

        Photo savedPhoto = photoRepository.save(photo);
        return convertToDTO(savedPhoto);
    }

    public PhotoDTO updatePhoto(Integer id, PhotoDTO photoDTO) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Photo non trouvée"));

        photo.setUrl(photoDTO.getUrl());
        photo.setDatePriseVue(photoDTO.getDatePriseVue());

        User auteur = userRepository.findById(photoDTO.getAuteurId())
                .orElseThrow(() -> new RuntimeException("Auteur non trouvé"));
        photo.setAuteur(auteur);

        Orchidee specimen = orchideeRepository.findById(photoDTO.getSpecimenId())
                .orElseThrow(() -> new RuntimeException("Spécimen non trouvé"));
        photo.setSpecimen(specimen);

        Photo updatedPhoto = photoRepository.save(photo);
        return convertToDTO(updatedPhoto);
    }

    public void deletePhoto(Integer id) {
        if (!photoRepository.existsById(id)) {
            throw new RuntimeException("Photo non trouvée");
        }
        photoRepository.deleteById(id);
    }
}