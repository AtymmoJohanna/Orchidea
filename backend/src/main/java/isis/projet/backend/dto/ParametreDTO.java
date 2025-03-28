package isis.projet.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ParametreDTO implements Serializable {

    private Integer id;
    private boolean camera;
    private boolean photos;
    private boolean localisation;
    private Integer userId;

}