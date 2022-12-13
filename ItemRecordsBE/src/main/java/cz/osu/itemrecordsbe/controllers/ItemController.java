package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.models.Item;
import cz.osu.itemrecordsbe.repositories.AppUserRepository;
import cz.osu.itemrecordsbe.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/items/")
@CrossOrigin
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    AppUserRepository userRepository;

    // TODO - rewrite ItemController methods + clean up + add ItemService
    @GetMapping("all")
    ResponseEntity<List<Item>> getAll() {
        List<Item> items = itemRepository.findAll();
        for (Item item : items) {
            if (item.getItemOffer() != null)
                item.getItemOffer().setItem(null);
            item.getUser().setItems(null);
            item.getUser().setComments(null);
            item.getUser().setInterestGroups(null);
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("all/{username}")
    ResponseEntity<List<Item>> getAllByUser(@PathVariable("username") String username) {
        AppUser retUser = userRepository.findByUsername(username);
        List<Item> ret = new ArrayList<>();
        List<Item> items = itemRepository.findAll();
        for (Item item : items) {
            if (item.getItemOffer() != null)
                item.getItemOffer().setItem(null);
            item.getUser().setItems(null);
            item.getUser().setComments(null);
            item.getUser().setInterestGroups(null);
            if (item.getUser().getUserId().equals(retUser.getUserId()))
                ret.add(item);
        }
        return ResponseEntity.ok(ret);
    }

    @GetMapping("get/{itemId}")
    ResponseEntity<Item> getItemById(@PathVariable("itemId") Long itemId) {
        Item item = itemRepository.findByItemId(itemId);
        item.setUser(null);
        return ResponseEntity.ok(item);
    }

    @PostMapping(value = "add/{userId}")
    ResponseEntity<String> addItem(@RequestBody Item item, @PathVariable("userId") Long userId) {
        if (item.isItemEmpty(item))
            return new ResponseEntity<>("Item is null", HttpStatus.BAD_REQUEST);
        else {
            Long lastId;
            if (itemRepository.findAll().isEmpty())
                lastId = 1L;
            else {
                lastId = itemRepository.findTopByOrderByItemIdDesc().getItemId();
                lastId++;
            }
            item.setItemId(lastId);
            //AppUser user = userRepository.findByUserId(userId);
            //item.setUser(user);
            itemRepository.save(item);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @PutMapping(value = "edit/{itemId}")
    ResponseEntity<String> updateItem(@RequestBody Item newItem, @PathVariable("itemId") Long itemId) {
        if (!itemRepository.existsById(itemId)) {
            System.out.println("ITEM ID NOT FOUND");
            return new ResponseEntity<>("Item not found", HttpStatus.BAD_REQUEST);
        } else {
            Item item = itemRepository.findByItemId(itemId);
            item.update(newItem);
            itemRepository.save(item);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

    @DeleteMapping(value = "delete/{itemId}")
    ResponseEntity<String> deleteItem(@PathVariable("itemId") Long itemId) {
        if (!itemRepository.existsById(itemId))
            return new ResponseEntity<>("Item not found", HttpStatus.BAD_REQUEST);
        else {
            Item item = itemRepository.findByItemId(itemId);
            item.setUser(null);
            itemRepository.delete(item);
            return new ResponseEntity<>("Item deleted", HttpStatus.OK);
        }
    }

}
