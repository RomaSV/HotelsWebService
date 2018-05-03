package api;

import hotels.Reservation;
import hotels.User;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface UserService {
    @RequestMapping(method = RequestMethod.GET, value = "/accounts")
    @Secured("ROLE_MANAGER")
    List<User> getUsers();

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{userName}")
    User getUser(@PathVariable String userName);

    @RequestMapping(method = RequestMethod.GET, value = "/accounts/{userName}/reservations")
    @PreAuthorize("#userName == authentication.name")
    List<Reservation> getReservations(@PathVariable String userName);
}
