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

    public PhotoDTO(Integer id, String url, LocalDate datePriseVue, Integer auteurId, Integer specimenId) {
        this.id = id;
        this.url = url;
        this.datePriseVue = datePriseVue;
        this.auteurId = auteurId;
        this.specimenId = specimenId;
    }

}