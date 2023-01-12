package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.security.payload.LoginRequest;
import cz.osu.itemrecordsbe.security.payload.SignupRequest;
import cz.osu.itemrecordsbe.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin
public class AppUserController {

    @Autowired
    private AppUserService userService;

    @GetMapping("all")
    ResponseEntity<Object> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("get/{userId}")
    ResponseEntity<Object> getUserDataByUserId(@PathVariable("userId") Long userId) {
        return userService.getUserDataByUserId(userId);
    }

    @PostMapping("signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        return userService.registerUser(signupRequest);
    }

    @PostMapping("signin")
    public ResponseEntity<Object> loginUser(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.loginUser(loginRequest);
    }

}
