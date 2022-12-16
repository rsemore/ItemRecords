package cz.osu.itemrecordsbe.services.impls;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.repositories.AppUserRepository;
import cz.osu.itemrecordsbe.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppUserServiceImpl implements AppUserService, UserDetailsService {

    @Autowired
    private AppUserRepository userRepository;

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

}
