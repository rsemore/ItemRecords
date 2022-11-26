package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.models.Item;
import cz.osu.itemrecordsbe.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/items/")
@CrossOrigin(origins = "http://localhost:4200/")
public class ItemController {

    @Autowired
    ItemRepository itemRepository;

    @GetMapping("all")
    ResponseEntity<List<Item>> getAll() {
        List<Item> items = itemRepository.findAll();
        for (Item item : items) {
            //item.setItemOffer(null);
            if(item.getItemOffer() != null)
                item.getItemOffer().setItem(null);
            //item.setUserId(null);
            item.getUserId().setItems(null);
            item.getUserId().setComments(null);
        }
        return ResponseEntity.ok(items);
    }

}
