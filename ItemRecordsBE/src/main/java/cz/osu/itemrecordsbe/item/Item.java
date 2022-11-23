package cz.osu.itemrecordsbe.item;

import cz.osu.itemrecordsbe.itemOffer.ItemOffer;
import cz.osu.itemrecordsbe.user.AppUser;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int itemId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser userId;

    @OneToOne(mappedBy = "itemId")
    private ItemOffer itemOffer;

    private String itemName;
    private String manufacturer;
    private int yearOfManufacture;
    private Category category;
    private String itemDescription;
    //private String photoUrl;

}
