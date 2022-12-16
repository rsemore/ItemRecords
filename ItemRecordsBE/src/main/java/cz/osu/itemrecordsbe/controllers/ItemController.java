package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.models.Item;
import cz.osu.itemrecordsbe.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items/")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("all")
    ResponseEntity<Object> getAll() {
        return itemService.getAllItems();
    }

    @GetMapping("all/{userId}")
    ResponseEntity<Object> getAllByUserId(@PathVariable("userId") Long userId) {
        return itemService.getAllItemsByUserId(userId);
    }

    @GetMapping("get/{itemId}")
    ResponseEntity<Object> getItemById(@PathVariable("itemId") Long itemId) {
        return itemService.getItemById(itemId);
    }

    @PostMapping(value = "add/{userId}")
    ResponseEntity<Object> addItem(@RequestBody Item item, @PathVariable("userId") Long userId) {
        return itemService.addItem(item, userId);
    }

    @PutMapping(value = "edit/{itemId}")
    ResponseEntity<Object> updateItem(@RequestBody Item newItem, @PathVariable("itemId") Long itemId) {
        return itemService.updateItem(newItem, itemId);
    }

    @DeleteMapping(value = "delete/{itemId}")
    ResponseEntity<Object> deleteItem(@PathVariable("itemId") Long itemId) {
        return itemService.deleteItem(itemId);
    }

}
