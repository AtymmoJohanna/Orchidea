package isis.projet.backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class EspeceOrchideeDTO implements Serializable {

    private Integer code;
    private String nomScientifique;

}