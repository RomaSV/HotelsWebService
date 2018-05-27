package feign;

import hotels.Reservation;
import hotels.User;

import java.util.List;
import java.util.Map;

@Headers("Content-Type: application/json")
public interface UsersClient {
    @RequestLine("POST /accounts")
    List<User> getUsers();

    @RequestLine("POST /accounts/{username}")
    User getUser(@Param("username") String userName);

    @RequestLine("POST /accounts/{username}/reservations")
    List<Reservation> getReservations(@Param("username") String userName, Map<String, String> params);
}
