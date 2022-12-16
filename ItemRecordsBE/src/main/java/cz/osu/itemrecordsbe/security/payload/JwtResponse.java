package cz.osu.itemrecordsbe.security.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtResponse {

    private String token;
    //@Value("${application.jwt.token-prefix}")
    //private String type;
    private Long userId;
    private String username;
    private String email;

    public JwtResponse(String token, Long userId, String username, String email) {
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.email = email;
    }
}
