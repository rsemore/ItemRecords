package cz.osu.itemrecordsbe.services.impls;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.repositories.AppUserRepository;
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
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public AppUser getUserById(Long userId) {
        return userRepository.findByUserId(userId);
    }

    @Override
    public AppUser getLoggedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = (String) auth.getPrincipal();
        return (AppUser) loadUserByUsername(username);
    }

    @Override
    public AppUser saveUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean existsByUserId(Long userId) {
        return userRepository.existsByUserId(userId);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public ResponseEntity<Object> getAllUsers() {
        List<AppUser> ret = userRepository.findAll();
        ret.forEach(user -> {
            user.setItems(null);
            user.setComments(null);
            user.setInterestGroups(null);
            user.setPassword(null);
        });

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> getUserDataByUserId(Long userId) {
        if (!userRepository.existsById(userId))
            return ResponseEntity.notFound().build();

        AppUser ret = userRepository.findByUserId(userId);
        ret.getItems().forEach(item -> {
            item.setUser(null);
            item.setItemOffer(null);
        });
        ret.getComments().forEach(comment -> comment.setUser(null));
        ret.getInterestGroups().forEach(interestGroup -> interestGroup.setAppUsers(null));
        ret.setPassword(null);

        return ResponseEntity.ok(ret);
    }

    @Override
    public ResponseEntity<Object> registerUser(SignupRequest signupRequest) {
        if (this.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body("Username is already taken!");
        }

        if (this.existsByEmail(signupRequest.getEmail())) {
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
        this.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Object> loginUser(LoginRequest loginRequest) {
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
