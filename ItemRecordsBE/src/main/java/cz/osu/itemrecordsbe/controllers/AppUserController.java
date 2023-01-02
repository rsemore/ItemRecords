package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.security.jwt.JwtUtils;
import cz.osu.itemrecordsbe.security.payload.JwtResponse;
import cz.osu.itemrecordsbe.security.payload.LoginRequest;
import cz.osu.itemrecordsbe.security.payload.SignupRequest;
import cz.osu.itemrecordsbe.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin
public class AppUserController {

    @Autowired
    private AppUserService userService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

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
        if (userService.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Username is already taken!");
        }

        if (userService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Email is already registered!");
        }

        // Create new user account
        AppUser user = new AppUser(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                encoder.encode(signupRequest.getPassword())
        );
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AppUser user = (AppUser) authentication.getPrincipal();
        user.setItems(null);
        user.setComments(null);

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                user.getUserId(),
                user.getUsername(),
                user.getEmail()
        ));
    }

}
