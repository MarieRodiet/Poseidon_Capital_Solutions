package com.nnk.springboot.services;

import com.nnk.springboot.domain.DBUser;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Transactional
public class UserServiceTests {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void findAllUsersTest() {
        // GIVEN
        DBUser user1 = createUser(1);
        DBUser user2 = createUser(2);
        List<DBUser> expectedUsers = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // WHEN
        List<DBUser> actualUsers = userService.findAll();

        // THEN
        assertThat(actualUsers).isEqualTo(expectedUsers);
    }

    @Test
    public void saveUserTest() {
        // GIVEN
        DBUser user = createUser(1);

        // WHEN
        userService.save(user);

        // THEN
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void deleteByIdUserTest() {
        // GIVEN
        int userId = 1;

        // WHEN
        userService.deleteById(userId);

        // THEN
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void findByIdUserTest() {
        // GIVEN
        int userId = 1;
        DBUser expectedUser = createUser(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(expectedUser));

        // WHEN
        DBUser actualUser = userService.findById(userId);

        // THEN
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    @Test
    public void findByIdUserNotFoundTest() {
        // GIVEN
        int userId = 1;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // WHEN
        DBUser actualUser = userService.findById(userId);

        // THEN
        assertThat(actualUser).isNull();
    }

    @Test
    public void findByUsernameTest() {
        // GIVEN
        String username = "testUser";
        DBUser expectedUser = createUser(1);
        when(userRepository.findByUsername(username)).thenReturn(expectedUser);

        // WHEN
        DBUser actualUser = userService.findByUsername(username);

        // THEN
        assertThat(actualUser).isEqualTo(expectedUser);
    }

    private DBUser createUser(int id) {
        DBUser user = new DBUser();
        user.setId(id);
        // Set other user properties as needed
        return user;
    }
}

