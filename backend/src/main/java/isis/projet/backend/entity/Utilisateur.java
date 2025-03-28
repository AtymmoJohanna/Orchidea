package isis.projet.backend.entity;

import java.util.LinkedList;
import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;


import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString

public class Utilisateur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE) // Empêche la modification de l'ID
    private Integer id;

    @NonNull
    @Email
    @Size(min = 5, max = 255)
    @Column(nullable = false, unique = true, length = 255)
    private String mail;

    @NonNull
    @Size(min = 8, max = 255)
    @Column(nullable = false, length = 255)
    private String pwd;

    @NonNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String nom;

    @NonNull
    @Size(min = 1, max = 255)
    @Column(nullable = false, length = 255)
    private String prenom;  // ✅ Ajout du prénom

    // 🔽 Ajout du constructeur explicite pour éviter l'erreur
    public Utilisateur(String mail, String pwd, String nom, String prenom) {
        this.mail = mail;
        this.pwd = pwd;
        this.nom = nom;
        this.prenom = prenom;
    }


}

