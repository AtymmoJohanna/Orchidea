package isis.projet.backend.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // la clé est auto-générée par la BD, On ne veut pas de "setter"
    private Integer id;

    @NonNull
    @Lob
    @Column(nullable = false, unique=true, columnDefinition = "TEXT")
    private String url;

    @Basic(optional = false)
    @Column(nullable = false)
    @ToString.Exclude
    private LocalDate datePriseVue ;



    @ManyToOne(optional = false)
    @NonNull
    private Utilisateur auteur ;

    @ManyToOne(optional = false)
    @NonNull
    private Orchidee specimen ;

}

