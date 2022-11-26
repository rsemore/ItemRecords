package cz.osu.itemrecordsbe.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public ItemOffer(Long offerId, int price, String description, LocalDateTime startDate, LocalDateTime endDate) {
        this.offerId = offerId;
        this.price = price;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
