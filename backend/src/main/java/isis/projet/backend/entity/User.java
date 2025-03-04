package isis.projet.backend.entity;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // la clé est auto-générée par la BD, On ne veut pas de "setter"
    private Integer id;

    @NonNull
    @Email
    @Size(min = 5, max = 255)  // min=5 pour éviter "a@b" qui est invalide
    @Column(nullable = false, unique = true, length = 255)
    private String mail;

    @NonNull
    @Size(min = 8, max = 255) // min=8 pour éviter les mots de passe trop courts
    @Column(nullable = false, unique = true, length = 255)
    private String pwd;

    @NonNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, unique=true, length = 255)
    private String nom;


    @ToString.Exclude
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "emetteur")
    private List<Avis> avis = new LinkedList<>();

    @OneToOne
    @NonNull
    private Parametre parametre;

    @ToString.Exclude
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "auteur")
    private List<Orchidee> specimens = new LinkedList<>();

    @ToString.Exclude
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "auteur")
    private List<Photo> photos = new LinkedList<>();


}

