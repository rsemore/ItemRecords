package cz.osu.itemrecordsbe.dataloader;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private AppUserRepository userRepository;

    @Autowired
    public DataLoader(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        userRepository.save(new AppUser(1L, "Batman", "bruce@wayne.com", "Bruce", "Wayne", "batman123"));
        userRepository.save(new AppUser(2L, "SH", "sherlock@holmes.com", "Sherlock", "Holmes", "mysteries"));
        userRepository.save(new AppUser(3L, "SteveJ", "steve@apple.com", "Steve", "Jobs", "Apple"));
    }
}
