package cz.osu.itemrecordsbe.services;

import cz.osu.itemrecordsbe.models.ItemOffer;
import org.springframework.http.ResponseEntity;

public interface ItemOfferService {

    ResponseEntity<Object> getAllItemOffers();

    ResponseEntity<Object> getItemOfferByOfferId(Long offerId);

    ResponseEntity<Object> createItemOffer(ItemOffer offer, Long itemId);

    ResponseEntity<Object> deleteItemOffer(Long offerId);

}
