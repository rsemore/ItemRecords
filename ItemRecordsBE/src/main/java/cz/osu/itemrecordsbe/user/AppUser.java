package cz.osu.itemrecordsbe.user;

import cz.osu.itemrecordsbe.comment.Comment;
import cz.osu.itemrecordsbe.item.Item;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    private String username;
    private String email;
    private String firstName;
    private String lastName;

    @OneToMany(mappedBy = "userId")
    private Set<Item> items;

    @OneToMany(mappedBy = "userId")
    private Set<Comment> comments;

    public AppUser() {}

    public AppUser(String username, String email, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
