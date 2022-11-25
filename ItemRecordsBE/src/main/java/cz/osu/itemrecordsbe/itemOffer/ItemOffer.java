package cz.osu.itemrecordsbe.itemOffer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import cz.osu.itemrecordsbe.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ItemOffer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long offerId;

    @OneToOne
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    private int price;
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;



}
