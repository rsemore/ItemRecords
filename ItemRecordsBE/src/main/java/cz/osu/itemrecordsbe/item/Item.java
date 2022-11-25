package cz.osu.itemrecordsbe.item;

import cz.osu.itemrecordsbe.itemOffer.ItemOffer;
import cz.osu.itemrecordsbe.user.AppUser;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser userId;

    @OneToOne(mappedBy = "item", cascade = CascadeType.REMOVE)
    private ItemOffer itemOffer;

    private String itemName;
    private String manufacturer;
    private int yearOfManufacture;
    private ECategory category;
    private String itemDescription;
    //private String photoUrl;

}
