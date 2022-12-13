package cz.osu.itemrecordsbe.services;

import cz.osu.itemrecordsbe.models.AppUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AppUserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    List<AppUser> getAllUsers();
    AppUser createUser(AppUser user);
    AppUser getUserById(Long userId);
    AppUser getLoggedUser();

    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}
