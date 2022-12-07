package cz.osu.itemrecordsbe.dataloader;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.models.InterestGroup;
import cz.osu.itemrecordsbe.repositories.AppUserRepository;
import cz.osu.itemrecordsbe.repositories.InterestGroupRepository;
import cz.osu.itemrecordsbe.repositories.ItemOfferRepository;
import cz.osu.itemrecordsbe.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    private AppUserRepository userRepository;
    private InterestGroupRepository interestGroupRepository;

    @Autowired
    public DataLoader(AppUserRepository userRepository, InterestGroupRepository interestGroupRepository) {
        this.userRepository = userRepository;
        this.interestGroupRepository = interestGroupRepository;
    }

    public void run(ApplicationArguments args) {
        /*userRepository.save(new AppUser(1L, "Batman", "bruce@wayne.com", "Bruce", "Wayne", "batman123"));
        userRepository.save(new AppUser(2L, "SH", "sherlock@holmes.com", "Sherlock", "Holmes", "mysteries"));
        userRepository.save(new AppUser(3L, "SteveJ", "steve@apple.com", "Steve", "Jobs", "Apple"));
        interestGroupRepository.save(new InterestGroup(1L, "Komiksy"));
        interestGroupRepository.save(new InterestGroup(2L, "Modely aut"));
        interestGroupRepository.save(new InterestGroup(3L, "Starožitnosti"));
        interestGroupRepository.save(new InterestGroup(4L, "Panenky"));
        interestGroupRepository.save(new InterestGroup(5L, "Odznáčky"));*/
    }


}
