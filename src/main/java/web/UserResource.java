package web;

import api.UserService;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import hotels.Reservation;
import hotels.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserResource implements UserService{
    Map<String, User> users = new HashMap<>();
    Config config;

    public UserResource() {
        config = ConfigFactory.load();
        for (long i = 1; i <= config.getObject("users").unwrapped().size(); i++) {
            Map<String, Object> userConf = config.getObject("users." + i).unwrapped();
            User user = new User(userConf.get("userName").toString(), userConf.get("name").toString());
            users.put(userConf.get("userName").toString(), user);
        }
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
    public List<Reservation> getUserReservations(@PathVariable("userName") String userName) {
        List<Reservation> result = new ArrayList<>();
        for (long i = 1; i <= config.getObject("reservations").unwrapped().size(); i++) {
            Map<String, Object> reservConf = config.getObject("reservations." + i).unwrapped();
            if (reservConf.get("userName").equals(userName)) {
                Reservation reservation =
                        new Reservation(i, Long.parseLong(reservConf.get("hotelId").toString()),
                                Integer.parseInt(reservConf.get("roomId").toString()), userName);
                reservation.setArrival(reservConf.get("arrival").toString());
                reservation.setDeparture(reservConf.get("department").toString());
                result.add(reservation);
            }
        }
        return result;
    }
}
