package cz.osu.itemrecordsbe.services;

import cz.osu.itemrecordsbe.models.Item;
import org.springframework.http.ResponseEntity;

public interface ItemService {
    Boolean existsByItemId(Long itemId);

    Item findItemByItemId(Long itemId);

    ResponseEntity<Object> getAllItems();

    ResponseEntity<Object> getAllItemsByUserId(Long userId);

    ResponseEntity<Object> getItemById(Long itemId);

    ResponseEntity<Object> addItem(Item item, Long userId);

    ResponseEntity<Object> updateItem(Item item, Long itemId);

    ResponseEntity<Object> deleteItem(Long itemId);
}
