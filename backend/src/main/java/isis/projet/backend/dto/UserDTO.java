package isis.projet.backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {

    private Integer id;
    private String mail;
    private String pwd;
    private String nom;
    private Integer parametreId;

}