package isis.projet.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParametreDTO {

    private Integer id;
    private boolean camera;
    private boolean photos;
    private boolean localisation;
    private Integer userId;

}