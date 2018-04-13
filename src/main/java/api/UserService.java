package api;

import hotels.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface UserService {
    @RequestMapping(method = RequestMethod.GET, value = "/users")
    List<User> getUsers();

    @RequestMapping(method = RequestMethod.GET, value = "/users/{nickName}")
    User getUser(@PathVariable String nickName);
}
