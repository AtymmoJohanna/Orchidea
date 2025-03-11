package isis.projet.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AvisDTO {

    private Integer id;
    private String commentaire;
    private String date;
    private String emetteurNom;

}