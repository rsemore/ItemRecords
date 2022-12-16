package cz.osu.itemrecordsbe.services.impls;

import cz.osu.itemrecordsbe.models.Item;
import cz.osu.itemrecordsbe.models.ItemOffer;
import cz.osu.itemrecordsbe.repositories.ItemOfferRepository;
import cz.osu.itemrecordsbe.services.ItemOfferService;
import cz.osu.itemrecordsbe.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemOfferServiceImpl implements ItemOfferService {

    @Autowired
    private ItemOfferRepository itemOfferRepository;

    @Autowired
    private ItemService itemService;

    @Override
    public ResponseEntity<Object> getAllItemOffers() {
        List<ItemOffer> ret = itemOfferRepository.findAll();
        ret.forEach(offer -> {
            offer.getItem().setItemOffer(null);
            offer.getItem().getUser().setComments(null);
            offer.getItem().getUser().setItems(null);
            offer.getItem().getUser().setInterestGroups(null);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> getItemOfferByOfferId(Long offerId) {
        if (!itemOfferRepository.existsById(offerId))
            return ResponseEntity.notFound().build();

        ItemOffer ret = itemOfferRepository.findByOfferId(offerId);
        ret.getItem().setItemOffer(null);
        ret.getItem().getUser().setItems(null);
        ret.getItem().getUser().setComments(null);
        ret.getItem().getUser().setInterestGroups(null);

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> createItemOffer(ItemOffer offer, Long itemId) {
        if (!itemService.existsByItemId(itemId))
            return ResponseEntity.notFound().build();

        Item item = itemService.findItemByItemId(itemId);
        offer.setItem(item);
        itemOfferRepository.save(offer);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> deleteItemOffer(Long offerId) {
        if (!itemOfferRepository.existsById(offerId))
            return ResponseEntity.notFound().build();

        ItemOffer offer = itemOfferRepository.findByOfferId(offerId);
        offer.setItem(null);
        itemOfferRepository.delete(offer);

        return ResponseEntity.ok().build();
    }
}
