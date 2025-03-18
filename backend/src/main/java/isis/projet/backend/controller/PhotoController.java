package isis.projet.backend.controller;

import isis.projet.backend.dao.OrchideeRepository;
import isis.projet.backend.dao.UtilisateurRepository;
import isis.projet.backend.dto.PhotoDTO;
import isis.projet.backend.entity.Orchidee;
import isis.projet.backend.entity.Utilisateur;
import isis.projet.backend.entity.Photo;
import isis.projet.backend.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;
    private final OrchideeRepository orchideeRepository;
    private final UtilisateurRepository utilisateurRepository;

    @PostMapping
    public ResponseEntity<PhotoDTO> createPhoto(@RequestBody PhotoDTO photoDTO) {

        // Récupérer l'orchidée à partir de l'identifiant specimenId
        Orchidee orchidee = orchideeRepository.findById(photoDTO.getSpecimenId())
                .orElseThrow(() -> new RuntimeException("Orchidée non trouvée"));

        // Récupérer l'auteur à partir de l'identifiant auteurId
        Utilisateur auteur = utilisateurRepository.findById(photoDTO.getAuteurId())
                .orElseThrow(() -> new RuntimeException("Auteur non trouvé"));

        // Créer l'entité Photo en utilisant l'URL, l'orchidée, l'auteur
        Photo photo = new Photo(photoDTO.getUrl(), auteur, orchidee);

        // Assigner la date de prise de vue si elle n'est pas fournie dans le DTO
        if (photoDTO.getDatePriseVue() != null) {
            photo.setDatePriseVue(photoDTO.getDatePriseVue());
        } else {
            photo.setDatePriseVue(LocalDate.now()); // Si pas de date spécifiée, on prend la date actuelle
        }

        // Sauvegarder la photo via le service
        Photo savedPhoto = photoService.createPhoto(photo);

        // Conversion de l'entité Photo en DTO
        PhotoDTO savedPhotoDTO = new PhotoDTO(
                savedPhoto.getId(),
                savedPhoto.getUrl(),
                savedPhoto.getDatePriseVue(),
                savedPhoto.getAuteur().getId(),
                savedPhoto.getSpecimen().getId()
        );

        // Retourner le DTO de la photo créée
        return ResponseEntity.ok(savedPhotoDTO);
    }
}