package cz.osu.itemrecordsbe.controllers;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.models.Comment;
import cz.osu.itemrecordsbe.models.Item;
import cz.osu.itemrecordsbe.security.jwt.JwtUtils;
import cz.osu.itemrecordsbe.security.PasswordConfig;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/users/")
@CrossOrigin
public class AppUserController {

    @Autowired
    private PasswordConfig passwordConfig;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    private final AppUserService userService;

    public AppUserController(AppUserService userService) {
        this.userService = userService;
    }

    @GetMapping("all")
    ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // TODO - getUserById
    /*@GetMapping("get/{userId}")
    ResponseEntity<AppUser> getUser(@PathVariable("userId") Long userId) {
        AppUser user = appUserRepository.findByUserId(userId);
        user.setItems(null);
        user.setComments(null);
        for (InterestGroup interest : user.getInterestGroups()) {
            interest.setAppUsers(null);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }*/

    @PostMapping("signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest) {
        if (userService.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Username is already taken!");
        }

        if (userService.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already registered!");
        }

        // Create new user account
        AppUser user = new AppUser(
                signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordConfig.passwordEncoder().encode(signupRequest.getPassword())
        );
        userService.createUser(user);
        return ResponseEntity.ok("User registered successfully!");
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
        List<String> items = user.getItems().stream()
                .map(Item::toString).toList();
        List<String> comments = user.getComments().stream()
                .map(Comment::toString).toList();

        return ResponseEntity.ok(new JwtResponse(
                jwt,
                user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                items,
                comments
        ));
    }

}
