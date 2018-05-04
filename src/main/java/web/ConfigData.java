package web;

import api.HotelUpdateRequest;
import api.RoomUpdateRequest;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import hotels.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * To work with mini data-base emulated in config
 */
public class ConfigData {
    private final Config config;

    public ConfigData() {
        config = ConfigFactory.load();
    }

    public HotelNetwork getConfiguredHotelNetwork() {
        HotelNetwork hotelNetwork = new HotelNetwork();
        for (long i = 1; i <= config.getObject("hotels").unwrapped().size(); i++) {
            Map<String, Object> hotel = config.getObject("hotels." + i).unwrapped();
            hotelNetwork.addHotel(hotel.get("adminName").toString());

            HotelUpdateRequest request = new HotelUpdateRequest();
            request.setName(hotel.get("name").toString());
            request.setDescription(hotel.get("description").toString());
            request.setStars((int)hotel.get("stars"));
            request.setWithRestaurant((boolean)hotel.get("hasRestaurant"));
            request.setCloseToCenter((boolean)hotel.get("closeToCenter"));

            hotelNetwork.updateHotel(i, request);

            for (int j = 1; j <= config.getObject("hotels." + i + ".rooms").unwrapped().size(); j++) {
                Map<String, Object> roomConf = config.getObject("hotels." + i + ".rooms." + j).unwrapped();

                RoomUpdateRequest roomRequest = new RoomUpdateRequest();
                roomRequest.setName(roomConf.get("name").toString());
                roomRequest.setDescription(roomConf.get("description").toString());
                roomRequest.setPrice(Double.parseDouble(roomConf.get("price").toString()));
                roomRequest.setNumberOfPeople((int)roomConf.get("numberOfPeople"));
                roomRequest.setWithBathroom((boolean)roomConf.get("hasBathroom"));
                roomRequest.setWithFridge((boolean)roomConf.get("hasFridge"));

                Room room = hotelNetwork.addRoom(i, roomRequest);
                ArrayList<String> dates = (ArrayList<String>)roomConf.get("bookedDays");
                room.book(dates.get(0), dates.get(dates.size() - 1));
            }
        }
        return hotelNetwork;
    }

    public Map<String, User> getUsers() {
        HashMap<String, User> result = new HashMap<>();
        for (long i = 1; i <= config.getObject("users").unwrapped().size(); i++) {
            Map<String, Object> userConf = config.getObject("users." + i).unwrapped();
            String role = userConf.get("role").toString();
            User user = new User(userConf.get("userName").toString(), userConf.get("name").toString(), getUserType(role));
            result.put(userConf.get("userName").toString(), user);
        }
        return result;
    }

    public List<Reservation> getReservations(User user) {
        switch (user.getType()) {
            case MANAGER:
                return getReservations();
            case ADMIN:
                return getReservationsBy("hotelId", getHotelIdByAdmin(user.getUserName()));
            default:
                return getReservationsBy("userName", user.getUserName());
        }
    }

    private List<Reservation> getReservationsBy(String filter, String value) {
        List<Reservation> result = new ArrayList<>();
        for (long i = 1; i <= config.getObject("reservations").unwrapped().size(); i++) {
            Map<String, Object> reservConf = config.getObject("reservations." + i).unwrapped();
            if (reservConf.get(filter).toString().equals(value)) {
                Reservation reservation = new Reservation(i, Long.parseLong(reservConf.get("hotelId").toString()),
                        Integer.parseInt(reservConf.get("roomId").toString()), reservConf.get("userName").toString());
                reservation.setArrival(reservConf.get("arrival").toString());
                reservation.setDeparture(reservConf.get("department").toString());
                result.add(reservation);
            }
        }
        return result;
    }

    private List<Reservation> getReservations() {
        List<Reservation> result = new ArrayList<>();
        for (long i = 1; i <= config.getObject("reservations").unwrapped().size(); i++) {
            Map<String, Object> reservConf = config.getObject("reservations." + i).unwrapped();

            Reservation reservation = new Reservation(i, Long.parseLong(reservConf.get("hotelId").toString()),
                    Integer.parseInt(reservConf.get("roomId").toString()), reservConf.get("userName").toString());
            reservation.setArrival(reservConf.get("arrival").toString());
            reservation.setDeparture(reservConf.get("department").toString());
            result.add(reservation);
        }
        return result;
    }

    private UserType getUserType(String type) {
        switch (type) {
            case "ROLE_ADMIN":
                return UserType.ADMIN;
            case "ROLE_MANAGER":
                return UserType.MANAGER;
            default:
                return UserType.USER;
        }
    }

    private String getHotelIdByAdmin(String adminName) {
        for (Long i = 1L; i <= config.getObject("hotels").unwrapped().size(); i++) {
            if (config.getString("hotels." + i + ".adminName").equals(adminName)) {
                return i.toString();
            }
        }
        return null;
    }
}
