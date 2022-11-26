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

    @GetMapping("all")
    ResponseEntity<List<Item>> getAll() {
        List<Item> items = itemRepository.findAll();
        for (Item item : items) {
            if(item.getItemOffer() != null)
                item.getItemOffer().setItem(null);
            item.getUser().setItems(null);
            item.getUser().setComments(null);
        }
        return ResponseEntity.ok(items);
    }

    @GetMapping("all/{username}")
    ResponseEntity<List<Item>> getAllByUser(@PathVariable("username") String username) {
        AppUser retUser = userRepository.findByUsername(username);
        List<Item> ret = new ArrayList<>();
        List<Item> items = itemRepository.findAll();
        for (Item item : items) {
            if(item.getItemOffer() != null)
                item.getItemOffer().setItem(null);
            item.getUser().setItems(null);
            item.getUser().setComments(null);
            if (item.getUser().getUserId().equals(retUser.getUserId()))
                ret.add(item);
        }
        return ResponseEntity.ok(ret);
    }

    // TODO - item add
    @PostMapping(value = "add")
    ResponseEntity<String> addItem(@RequestBody Item item, @RequestBody AppUser user) {
        if (item.isItemEmpty(item))
            return new ResponseEntity<>("Item is null", HttpStatus.BAD_REQUEST);
        else {
            item.setUser(user);
            Long lastId = itemRepository.findTopByOrderByItemIdDesc().getItemId();
            lastId++;
            item.setItemId(lastId);
            itemRepository.save(item);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }

}
