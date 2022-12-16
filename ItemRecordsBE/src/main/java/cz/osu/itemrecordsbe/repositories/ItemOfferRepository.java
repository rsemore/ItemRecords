package cz.osu.itemrecordsbe.repositories;

import cz.osu.itemrecordsbe.models.ItemOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemOfferRepository extends JpaRepository<ItemOffer, Long> {

    ItemOffer findByOfferId(Long offerId);

}
