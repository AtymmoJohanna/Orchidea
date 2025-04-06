package isis.projet.backend.controller;

import isis.projet.backend.entity.Photo;
import isis.projet.backend.dao.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@RestController
@RequestMapping("/api/photos")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from the frontend
@RequiredArgsConstructor
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    @PostMapping
    public Photo savePhoto(@RequestBody Photo photo) {
        photo.setDatePriseVue(LocalDate.now()); // Set the current date
        return photoRepository.save(photo); // Save the photo to the database
    }
    public ResponseEntity<Photo> getPhoto(@PathVariable Integer id) {
        Optional<Photo> photoOptional = photoRepository.findById(id);
        return photoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
