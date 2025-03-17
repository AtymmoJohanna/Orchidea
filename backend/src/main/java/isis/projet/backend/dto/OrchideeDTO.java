package isis.projet.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrchideeDTO {

    private Integer id;
    private String commentaire;
    private Integer latitude;
    private Integer longitude;
    private LocalDate dateEnreg;
    private String etat;
    private List<String> couleur;
    private String varTaxon;
    private String nbreIndividu;
    private List<String> motif;
    private String forme;
    private Integer especeCode;
    private Integer auteurId;

}