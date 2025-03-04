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

public class Parametre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // la clé est auto-générée par la BD, On ne veut pas de "setter"
    private Integer id;

    @NonNull
    @Column()
    private boolean camera;

    @NonNull
    @Column()
    private boolean photos;

    @NonNull
    @Column()
    private boolean localisation;


    @OneToOne
    @NonNull
    private User user;

}

