package cz.osu.itemrecordsbe.security.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {

    private String token;
    private String type = "${application.jwt.token-prefix}";
    private Long id;
    private String username;
    private String email;
    private List<String> items;
    private List<String> comments;

    public JwtResponse(String token, Long id, String username, String email, List<String> items, List<String> comments) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.email = email;
        this.items = items;
        this.comments = comments;
    }
}
