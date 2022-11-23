package cz.osu.itemrecordsbe.user;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private List<AppUser> users = List.of(
            new AppUser("Batman13", "bruce@wayne.com", "Bruce", "Wayne")
    );

    public List<AppUser> getAll() {
        return users;
    }
}
