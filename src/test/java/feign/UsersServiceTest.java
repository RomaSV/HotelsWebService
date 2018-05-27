package feign;

import hotels.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UsersServiceTest {
    private UsersClient usersClient;

    @BeforeEach
    public void setHotelsClient() {
        UserFeignClientBuilder builder = new UserFeignClientBuilder();
        usersClient = builder.createUsersClient("Yellow", "BANANA");
    }
    @Test
    public void getHotels() {
        List<User> users = usersClient.getUsers();
        assertEquals(6, users.size());
    }
}
