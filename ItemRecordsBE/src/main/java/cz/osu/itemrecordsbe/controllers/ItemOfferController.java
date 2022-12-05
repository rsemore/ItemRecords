package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.models.Item;
import cz.osu.itemrecordsbe.models.ItemOffer;
import cz.osu.itemrecordsbe.repositories.ItemOfferRepository;
import cz.osu.itemrecordsbe.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/offers/")
@CrossOrigin
public class ItemOfferController {

    @Autowired
    ItemOfferRepository itemOfferRepository;

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("all")
    ResponseEntity<List<ItemOffer>> getAll() {
        List<ItemOffer> offers = itemOfferRepository.findAll();
        for (ItemOffer offer : offers) {
            offer.getItem().setItemOffer(null);
            //offer.getItem().setUser(null);
            offer.getItem().getUser().setComments(null);
            offer.getItem().getUser().setItems(null);
        }
        return ResponseEntity.ok(offers);
    }

    @GetMapping(value="get/{offerId}")
    ResponseEntity<ItemOffer> getCommentById(@PathVariable("offerId") Long offerId) {
        ItemOffer offer = itemOfferRepository.findByOfferId(offerId);
        offer.getItem().setItemOffer(null);
        offer.getItem().getUser().setItems(null);
        offer.getItem().getUser().setComments(null);
        return ResponseEntity.ok(offer);
    }

    @PostMapping(value = "sell/{itemId}")
    ResponseEntity<String> sellItem(@RequestBody ItemOffer itemOffer, @PathVariable("itemId") Long itemId) {
        /*if (itemOffer.isItemEmpty(item))
            return new ResponseEntity<>("Item is null", HttpStatus.BAD_REQUEST);
        else {*/
            Long lastId;
            if (itemOfferRepository.findAll().isEmpty())
                lastId = 1L;
            else {
                lastId = itemOfferRepository.findTopByOrderByOfferIdDesc().getOfferId();
                lastId++;
            }
            itemOffer.setOfferId(lastId);
            Item item = itemRepository.findByItemId(itemId);
            itemOffer.setItem(item);
            itemOfferRepository.save(itemOffer);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
       // }
    }

    @DeleteMapping(value = "delete/{offerId}")
    ResponseEntity<String> deleteOffer(@PathVariable("offerId") Long offerId) {
        if (!itemOfferRepository.existsById(offerId))
            return new ResponseEntity<>("Offer not found", HttpStatus.BAD_REQUEST);
        else {
            ItemOffer offer = itemOfferRepository.findByOfferId(offerId);
            offer.setItem(null);
            itemOfferRepository.delete(offer);
            return new ResponseEntity<>("Offer deleted", HttpStatus.OK);
        }
    }


}
