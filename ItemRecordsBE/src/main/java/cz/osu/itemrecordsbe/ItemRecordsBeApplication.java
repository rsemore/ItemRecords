package cz.osu.itemrecordsbe;

import cz.osu.itemrecordsbe.user.AppUser;
import cz.osu.itemrecordsbe.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ItemRecordsBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItemRecordsBeApplication.class, args);
    }

}