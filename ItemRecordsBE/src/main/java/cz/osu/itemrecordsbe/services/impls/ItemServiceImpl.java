package cz.osu.itemrecordsbe.services.impls;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.models.Item;
import cz.osu.itemrecordsbe.repositories.ItemRepository;
import cz.osu.itemrecordsbe.services.AppUserService;
import cz.osu.itemrecordsbe.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private AppUserService userService;

    @Override
    public Boolean existsByItemId(Long itemId) {
        return itemRepository.existsById(itemId);
    }

    @Override
    public Item findItemByItemId(Long itemId) {
        return itemRepository.findByItemId(itemId);
    }

    @Override
    public ResponseEntity<Object> getAllItems() {
        List<Item> ret = itemRepository.findAll();
        ret.forEach(item -> {
            if (item.getItemOffer() != null)
                item.getItemOffer().setItem(null);
            item.getUser().setItems(null);
            item.getUser().setComments(null);
            item.getUser().setInterestGroups(null);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> getAllItemsByUserId(Long userId) {
        if (!userService.existsByUserId(userId))
            return ResponseEntity.notFound().build();

        AppUser user = userService.getUserById(userId);
        List<Item> items = itemRepository.findAll();

        List<Item> ret = new ArrayList<>();
        items.forEach(item -> {
            if (item.getItemOffer() != null)
                item.getItemOffer().setItem(null);
            item.getUser().setItems(null);
            item.getUser().setComments(null);
            item.getUser().setInterestGroups(null);
            if (item.getUser().getUserId().equals(user.getUserId()))
                ret.add(item);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> getItemById(Long itemId) {
        if (!itemRepository.existsById(itemId))
            return ResponseEntity.notFound().build();

        Item ret = itemRepository.findByItemId(itemId);
        ret.setUser(null);
        ret.setItemOffer(null);

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> addItem(Item item, Long userId) {
        if (!userService.existsByUserId(userId))
            return ResponseEntity.notFound().build();

        AppUser user = userService.getUserById(userId);
        item.setUser(user);
        itemRepository.save(item);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> updateItem(Item newItem, Long itemId) {
        if (!itemRepository.existsById(itemId))
            return ResponseEntity.notFound().build();

        Item item = itemRepository.findByItemId(itemId);
        item.update(newItem);
        itemRepository.save(item);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> deleteItem(Long itemId) {
        if (!itemRepository.existsById(itemId))
            return ResponseEntity.notFound().build();

        Item item = itemRepository.findByItemId(itemId);
        item.setUser(null);
        itemRepository.delete(item);

        return ResponseEntity.ok().build();
    }
}
