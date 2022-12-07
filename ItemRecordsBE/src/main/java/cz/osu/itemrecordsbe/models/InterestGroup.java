package cz.osu.itemrecordsbe.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class InterestGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private Long groupId;

    private String groupName;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "interestGroups")
    private Set<AppUser> appUsers;

    public InterestGroup(Long groupId, String groupName) {
        this.groupId = groupId;
        this.groupName = groupName;
    }
}
