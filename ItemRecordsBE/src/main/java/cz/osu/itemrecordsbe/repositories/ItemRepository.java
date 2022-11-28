package cz.osu.itemrecordsbe.repositories;

import cz.osu.itemrecordsbe.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    Item findTopByOrderByItemIdDesc();

    Item findByItemId(Long itemId);

}
