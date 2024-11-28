package hueHarmony.web.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserAuthDto {

    private int userId;
    private String password;
    private List<String> roles;

    public UserAuthDto(int userId, String password, Object roles) {

        this.userId = userId;
        this.password = password;
        this.roles = List.of(roles.toString().split(","));
    }
}
