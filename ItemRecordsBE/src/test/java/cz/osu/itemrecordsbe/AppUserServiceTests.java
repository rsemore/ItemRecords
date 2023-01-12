package cz.osu.itemrecordsbe;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.repositories.AppUserRepository;
import cz.osu.itemrecordsbe.security.payload.SignupRequest;
import cz.osu.itemrecordsbe.services.impls.AppUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AppUserServiceTests {

    @Mock
    private AppUserRepository userRepository;

    @InjectMocks
    private AppUserServiceImpl userService;

    @Test
    void shouldCreateUserSuccessfully() {
        final AppUser user = new AppUser("batman", "bruce@wayne.com", "heslo123");

        given(userRepository.save(user)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        AppUser savedUser = userService.saveUser(user);

        assertThat(savedUser).isNotNull();
        verify(userRepository).save(any(AppUser.class));
    }

    @Test
    void shouldThrowErrorWhenSaveUserWithExistingUsername() {
        final AppUser user = new AppUser("batman", "bruce@wayne.com", "heslo123");

        given(userRepository.save(user)).willAnswer(invocationOnMock -> invocationOnMock.getArgument(0));

        userService.saveUser(user);

        SignupRequest signupRequest = new SignupRequest("batman", "email@email.com", "heslo123");
        ResponseEntity<Object> expected = userService.registerUser(signupRequest);  // TODO - encoder is null??

        assertEquals(expected, ResponseEntity.badRequest().body("Username is already taken!"));
    }

    @Test
    void shouldReturnGetAllUsers() {
        List<AppUser> users = new ArrayList<>();
        users.add(new AppUser(1L, "batman", "bruce@wayne.com", "heslo123", null, null, null, true, true, true, true));
        users.add(new AppUser(2L, "sherlock", "sherlock@holmes.com", "heslo123", null, null, null, true, true, true, true));

        given(userRepository.findAll()).willReturn(users);
        ResponseEntity<Object> expected = userService.getAllUsers();

        assertEquals(expected, ResponseEntity.ok(users));
    }

}
