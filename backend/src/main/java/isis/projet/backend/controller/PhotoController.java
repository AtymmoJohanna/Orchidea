package isis.projet.backend.controller;

import isis.projet.backend.entity.Photo;
import isis.projet.backend.dao.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/photos")
@CrossOrigin(origins = "http://localhost:5173") // Allow requests from the frontend
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    @PostMapping
    public Photo savePhoto(@RequestBody Photo photo) {
        photo.setDatePriseVue(LocalDate.now()); // Set the current date
        return photoRepository.save(photo); // Save the photo to the database
    }
}