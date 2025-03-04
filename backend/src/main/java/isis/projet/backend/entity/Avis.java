package isis.projet.backend.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString


public class Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // la clé est auto-générée par la BD, On ne veut pas de "setter"
    private Integer id;

    @NonNull
    @Size(min = 1, max = 255)
    @Column(unique=true, length = 255)
    private String commentaire;

    @Basic(optional = false)
    @Column(nullable = false)
    @ToString.Exclude
    // Initialisée avec la date de création
    private LocalDate date = LocalDate.now();

    @ManyToOne(optional = false)
    @NonNull
    private User emetteur ;

}

