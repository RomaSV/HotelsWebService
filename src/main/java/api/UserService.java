package api;

import hotels.Reservation;
import hotels.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface UserService {
    @RequestMapping(method = RequestMethod.POST, value = "/accounts")
    @Secured("ROLE_MANAGER")
    List<User> getUsers();

    @RequestMapping(method = RequestMethod.POST, value = "/accounts/{userName}")
    User getUser(@PathVariable String userName);

    @RequestMapping(method = RequestMethod.POST, value = "/accounts/{userName}/reservations")
    @PreAuthorize("#userName == authentication.name")
    List<Reservation> getReservations(@PathVariable String userName, @RequestParam Map<String, String> params);
}
