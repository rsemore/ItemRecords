package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.models.ItemOffer;
import cz.osu.itemrecordsbe.services.ItemOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/offers/")
@CrossOrigin
public class ItemOfferController {

    @Autowired
    private ItemOfferService itemOfferService;

    @GetMapping("all")
    ResponseEntity<Object> getAllItemOffers() {
        return itemOfferService.getAllItemOffers();
    }

    @GetMapping(value = "get/{offerId}")
    ResponseEntity<Object> getItemOfferByOfferId(@PathVariable("offerId") Long offerId) {
        return itemOfferService.getItemOfferByOfferId(offerId);
    }

    @PostMapping(value = "sell/{itemId}")
    ResponseEntity<Object> createItemOffer(@RequestBody ItemOffer itemOffer, @PathVariable("itemId") Long itemId) {
        return itemOfferService.createItemOffer(itemOffer, itemId);
    }

    @DeleteMapping(value = "delete/{offerId}")
    ResponseEntity<Object> deleteItemOffer(@PathVariable("offerId") Long offerId) {
        return itemOfferService.deleteItemOffer(offerId);
    }

}
