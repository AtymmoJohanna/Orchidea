package isis.projet.backend.controller;

import isis.projet.backend.dto.PhotoDTO;
import isis.projet.backend.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/photos")
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @PostMapping
    public ResponseEntity<PhotoDTO> createPhoto(@RequestBody PhotoDTO photoDTO) {
        PhotoDTO createdPhoto = photoService.createPhoto(photoDTO);
        return ResponseEntity.ok(createdPhoto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PhotoDTO> getPhoto(@PathVariable Integer id) {
        PhotoDTO photo = photoService.getPhotoById(id);
        return photo != null ? ResponseEntity.ok(photo) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<PhotoDTO>> getAllPhotos() {
        List<PhotoDTO> photos = photoService.getAllPhotos();
        return ResponseEntity.ok(photos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PhotoDTO> updatePhoto(@PathVariable Integer id, @RequestBody PhotoDTO photoDTO) {
        PhotoDTO updatedPhoto = photoService.updatePhoto(id, photoDTO);
        return ResponseEntity.ok(updatedPhoto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePhoto(@PathVariable Integer id) {
        photoService.deletePhoto(id);
        return ResponseEntity.noContent().build();
    }
}
