package isis.projet.backend.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PhotoDTO {

    private Integer id;
    private String url;
    private LocalDate datePriseVue;
    private Integer auteurId;
    private Integer specimenId;

}