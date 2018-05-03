package web;

import api.UserService;
import hotels.Reservation;
import hotels.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class UserResource implements UserService{
    private final Map<String, User> users;
    private final ConfigData configData;

    public UserResource() {
        configData = new ConfigData();
        users = configData.getUsers();
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUser(@PathVariable("userName") String userName) {
        return users.getOrDefault(userName, null);
    }

    @Override
    public List<Reservation> getReservations(@PathVariable("userName") String userName) {
        return configData.getReservations(getUser(userName));
    }
}
