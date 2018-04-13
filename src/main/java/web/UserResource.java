package web;

import api.UserService;
import hotels.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserResource implements UserService{
    Map<String, User> users = new HashMap<>();

    @Override
    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public User getUser(String nickName) {
        return users.getOrDefault(nickName, null);
    }
}
