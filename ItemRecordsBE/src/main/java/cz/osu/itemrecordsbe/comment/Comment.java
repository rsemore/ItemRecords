package cz.osu.itemrecordsbe.comment;

import cz.osu.itemrecordsbe.user.AppUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser userId;

    private String author;
    private String content;
}
