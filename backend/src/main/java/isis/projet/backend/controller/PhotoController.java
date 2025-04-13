package isis.projet.backend.controller;

import isis.projet.backend.entity.Photo;
import isis.projet.backend.dao.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/photos")
@CrossOrigin(
        origins = "http://localhost:5173",
        allowedHeaders = "*",
        allowCredentials = "true"
)
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoRepository photoRepository;

    // Endpoint pour récupérer toutes les photos
    @GetMapping
    public List<Photo> getAllPhotos() {
        return photoRepository.findAll();
    }

    // Endpoint pour récupérer une photo par ID
    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhoto(@PathVariable Integer id) {
        Optional<Photo> photoOptional = photoRepository.findById(id);
        return photoOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Endpoint pour créer une nouvelle photo
    @PostMapping
    public Photo savePhoto(@RequestBody Photo photo) {
        photo.setDatePriseVue(LocalDate.now());
        return photoRepository.save(photo);
    }
}