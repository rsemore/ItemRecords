package cz.osu.itemrecordsbe.itemOffer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/offers/")
@CrossOrigin(origins = "http://localhost:4200/")
public class ItemOfferController {

    @Autowired
    ItemOfferRepository itemOfferRepository;

    @GetMapping("all")
    ResponseEntity<List<ItemOffer>> getAll() {
        List<ItemOffer> offers = itemOfferRepository.findAll();
        for (ItemOffer offer : offers) {
            offer.getItem().setItemOffer(null);
            offer.getItem().setUserId(null);
        }
        return ResponseEntity.ok(offers);
    }


}
