package cz.osu.itemrecordsbe.services;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.security.payload.LoginRequest;
import cz.osu.itemrecordsbe.security.payload.SignupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AppUserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    AppUser getUserById(Long userId);

    AppUser getLoggedUser();

    AppUser saveUser(AppUser user);

    Boolean existsByUserId(Long userId);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    ResponseEntity<Object> getAllUsers();

    ResponseEntity<Object> getUserDataByUserId(Long userId);

    ResponseEntity<Object> registerUser(SignupRequest signupRequest);

    ResponseEntity<Object> loginUser(LoginRequest loginRequest);

}
