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

    public AvisDTO(Avis avis) {
        this.id = avis.getId();
        this.commentaire = avis.getCommentaire();
        this.date = avis.getDate().toString(); // Assuming you want the date as a String
        this.emetteurId = avis.getEmetteur().getId(); // Assuming you're using the ID of User as emetteurId
    }

}