package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin(origins = "http://localhost:4200/")
public class AppUserController {

    @Autowired
    private AppUserRepository appUserRepository;

    @PostMapping("login")
    ResponseEntity<?> loginUser(@RequestBody AppUser userData) {
        AppUser user = appUserRepository.findByUsername(userData.getUsername());
        user.setItems(null);    // reseni nekonecne rekurze
        user.setComments(null);
        if (user.getPassword().equals(userData.getPassword()))
            return new ResponseEntity<>(user, HttpStatus.OK);
        return new ResponseEntity<>("Invalid username/password", HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "add")
    ResponseEntity<String> addUser(@RequestBody AppUser user) {
        if (user.isUserEmpty(user))
            return new ResponseEntity<>("User is null", HttpStatus.BAD_REQUEST);
        if (appUserRepository.existsByEmail(user.getEmail()))
            return new ResponseEntity<>("Email already registered", HttpStatus.BAD_REQUEST);
        if (appUserRepository.existsByUsername(user.getUsername()))
            return new ResponseEntity<>("Username already taken", HttpStatus.BAD_REQUEST);
        else {
            Long lastId;
            if (appUserRepository.findAll().isEmpty())
                lastId = 1L;
            else {
                lastId = appUserRepository.findTopByOrderByUserIdDesc().getUserId();
                lastId++;
            }
            user.setUserId(lastId);
            appUserRepository.save(user);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
    }


}
