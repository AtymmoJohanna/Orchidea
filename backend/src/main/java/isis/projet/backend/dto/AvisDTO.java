package isis.projet.backend.dto;

import isis.projet.backend.entity.Avis;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class AvisDTO implements Serializable {

    private Integer id;
    private String commentaire;
    private String date;
    private Integer emetteurId;
    private String emetteurNom; // <-- ajoute ce champ

    public AvisDTO() {}

    public AvisDTO(Avis avis) {
        this.id = avis.getId();
        this.commentaire = avis.getCommentaire();
        this.date = avis.getDate().toString();
        this.emetteurId = avis.getEmetteur().getId();
        this.emetteurNom = avis.getEmetteur().getNom(); // Assure-toi que "getNom()" existe
    }
}

