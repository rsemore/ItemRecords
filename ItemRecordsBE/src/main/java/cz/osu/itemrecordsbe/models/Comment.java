package cz.osu.itemrecordsbe.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    private String author;
    private String content;

    public Comment(Long commentId, String author, String content) {
        this.commentId = commentId;
        this.author = author;
        this.content = content;
    }
}
