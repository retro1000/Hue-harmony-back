package hueHarmony.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserAuthDto {

    private int userid;
    private String password;
    private List<String> roles;

    public UserAuthDto(int userid, String password, Object roles) {

        this.userid = userid;
        this.password = password;
        this.roles = List.of(roles.toString().split(","));
    }
}
