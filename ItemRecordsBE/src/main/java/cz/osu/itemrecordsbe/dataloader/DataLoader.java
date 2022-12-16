package cz.osu.itemrecordsbe.dataloader;

import cz.osu.itemrecordsbe.models.InterestGroup;
import cz.osu.itemrecordsbe.repositories.AppUserRepository;
import cz.osu.itemrecordsbe.repositories.InterestGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private InterestGroupRepository interestGroupRepository;

    @Autowired
    public DataLoader(InterestGroupRepository interestGroupRepository) {
        this.interestGroupRepository = interestGroupRepository;
    }

    public void run(ApplicationArguments args) {
        interestGroupRepository.save(new InterestGroup(1L, "Komiksy"));
        interestGroupRepository.save(new InterestGroup(2L, "Modely aut"));
        interestGroupRepository.save(new InterestGroup(3L, "Starožitnosti"));
        interestGroupRepository.save(new InterestGroup(4L, "Panenky"));
        interestGroupRepository.save(new InterestGroup(5L, "Odznáčky"));
        interestGroupRepository.save(new InterestGroup(6L, "Známky"));
        interestGroupRepository.save(new InterestGroup(7L, "Figurky"));
        interestGroupRepository.save(new InterestGroup(8L, "Knihy"));
    }


}
