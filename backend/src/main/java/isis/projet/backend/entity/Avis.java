package isis.projet.backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import lombok.*;

@Entity
@Getter
@Setter
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
    private LocalDateTime date = LocalDateTime.now();

    @ManyToOne(optional = false)
    @NonNull
    private Utilisateur emetteur ;

    public Avis() {}

    public Avis(String commentaire, Utilisateur emetteur) {
        this.commentaire = commentaire;
        this.emetteur = emetteur;
        this.date = LocalDateTime.now();}
}

