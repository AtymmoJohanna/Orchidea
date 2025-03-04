package isis.projet.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString

public class Orchidee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // la clé est autogénérée par la BD, On ne veut pas de "setter"
    private Integer id;

    @NonNull
    @Column(length = 255)
    @Size(min = 1, max = 255)
    private String commentaire;

    @Basic(optional = false)
    @Column(nullable = false)
    @NonNull
    private Integer latitude;

    @Basic(optional = false)
    @Column(nullable = false)
    @NonNull
    private Integer longitude;

    @Basic(optional = false)
    @Column(nullable = false)
    @ToString.Exclude
    // Initialisée avec la date de création
    private LocalDate dateEnreg = LocalDate.now();

    @Enumerated(EnumType.STRING)
    private EtatInflorescence etat;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Couleur> couleur;

    @Enumerated(EnumType.STRING)
    private VariabiliteTaxon varTaxon;

    @Enumerated(EnumType.STRING)
    private NbreIndividu nbreIndividu;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private List<Motif> motif;

    @Enumerated(EnumType.STRING)
    private Forme forme;


    @ManyToOne(optional = false)
    @NonNull
    private EspeceOrchidee espece ;

    @ToString.Exclude
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "specimen")
    private List<Photo> photos = new LinkedList<>();

    @ManyToOne(optional = false)
    @NonNull
    private User auteur ;


}
