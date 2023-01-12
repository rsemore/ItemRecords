package cz.osu.itemrecordsbe;

import cz.osu.itemrecordsbe.models.AppUser;
import cz.osu.itemrecordsbe.repositories.AppUserRepository;
import cz.osu.itemrecordsbe.services.AppUserService;
import cz.osu.itemrecordsbe.services.impls.AppUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
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

}
