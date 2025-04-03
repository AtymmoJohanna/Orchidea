package isis.projet.backend.dto;

import isis.projet.backend.entity.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrchideeDTO implements Serializable {

    private Integer id;
    private String commentaire;
    private float latitude;
    private float longitude;
    private LocalDate dateEnreg;
    private EtatInflorescence etat;
    private List<Couleur> couleur;
    private VariabiliteTaxon varTaxon;
    private NbreIndividu nbreIndividu;
    private List<Motif> motif;
    private Forme forme;
    private Integer especeCode;
    private Integer auteurId;

}