package cz.osu.itemrecordsbe.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private AppUser user;

    @OneToOne(mappedBy = "item", cascade = CascadeType.REMOVE)
    private ItemOffer itemOffer;

    private String itemName;
    private String manufacturer;
    private int yearOfManufacture;
    private ECategory category;
    private String itemDescription;
    //private String photoUrl;

    public Item(Long itemId, String itemName, String manufacturer, int yearOfManufacture, ECategory category, String itemDescription) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.manufacturer = manufacturer;
        this.yearOfManufacture = yearOfManufacture;
        this.category = category;
        this.itemDescription = itemDescription;
    }

    public boolean isItemEmpty(Item item) {
        if (item.getItemName() == null || item.getItemName().equals(""))
            return true;
        if (item.getManufacturer() == null || item.getManufacturer().equals(""))
            return true;
        if (item.getYearOfManufacture() <= 0)
            return true;
        return false;
    }
}
