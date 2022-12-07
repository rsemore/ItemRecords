package cz.osu.itemrecordsbe.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "app_user_interests",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<InterestGroup> interestGroups;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Item> items;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private Set<Comment> comments;

    public AppUser(Long userId, String username, String email, String firstName, String lastName, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public boolean isUserEmpty(AppUser user) {
        if(user.getUsername() == null || user.getUsername().equals(""))
            return true;
        if (user.getPassword() == null || user.getPassword().equals(""))
            return true;
        if (user.getEmail() == null || user.getEmail().equals(""))
            return true;
        return false;
    }

    public void addInterestGroup(InterestGroup interestGroup) {
        this.interestGroups.add(interestGroup);
        interestGroup.getAppUsers().add(this);
    }
}
