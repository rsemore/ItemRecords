package cz.osu.itemrecordsbe.itemOffer;

import cz.osu.itemrecordsbe.item.Item;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ItemOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int offerId;

    @OneToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item itemId;

    private int price;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;



}
