package isis.projet.backend.entity;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @RequiredArgsConstructor @ToString

public class EspeceOrchidee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // la clé est auto-générée par la BD, On ne veut pas de "setter"
    private Integer code;

    @NonNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, unique=true, length = 255)
    private String nomScientifique;

    @ToString.Exclude
    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "espece")
    private List<Orchidee> specimens = new LinkedList<>();

}