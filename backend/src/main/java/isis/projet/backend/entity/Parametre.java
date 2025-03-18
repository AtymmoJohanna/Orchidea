package isis.projet.backend.entity;

import jakarta.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString

public class Parametre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // la clé est auto-générée par la BD, On ne veut pas de "setter"
    private Integer id;

    @NonNull
    @Column()
    private Boolean camera;

    @NonNull
    @Column()
    private Boolean photos;

    @NonNull
    @Column()
    private Boolean localisation;


    @OneToOne
    @NonNull
    private Utilisateur utilisateur;

}

