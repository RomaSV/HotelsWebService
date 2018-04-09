package hotels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

public class HotelNetwork {
    private final Map<Long, Hotel> hotels;
    private final AtomicLong hotelCounter;

    public HotelNetwork() {
        hotels = new HashMap<>();
        hotelCounter = new AtomicLong();
    }

    public List<Hotel> getHotels() {
        return new ArrayList<>(hotels.values());
    }

    public Hotel getHotel(long hotelId) {
        return hotels.getOrDefault(hotelId, null);
    }

    public List<Room> getRooms(long hotelId) {
        return new ArrayList<>(getHotel(hotelId).getRooms().values());
    }

    public Room getRoom(long hotelId, int roomId) {
        return getHotel(hotelId).getRooms().getOrDefault(roomId, null);
    }

    public Hotel addHotel(long adminId) {
        User user = new User(1, "", "", UserType.MANAGER); //ВРЕМЕННО, пока нет авторизации

        if (user.getType().equals(UserType.MANAGER)) {
            Hotel hotel = new Hotel(hotelCounter.incrementAndGet(), adminId);
            hotels.put(hotel.getId(), hotel);
            return hotel;
        }
        return null;
    }

    public Room addRoom(long hotelId, String name, String descr, double price) {
        User user = new User(1, "", "", UserType.MANAGER); //ВРЕМЕННО, пока нет авторизации

        if (user.getType().equals(UserType.MANAGER)) {
            return getHotel(hotelId).addRoom(name, descr, price);
        }
        return null;
    }

    public Hotel updateHotel(long hotelId, String name, String descr) {
        User user = new User(1, "", "", UserType.MANAGER); //ВРЕМЕННО, пока нет авторизации

        if (user.getType().equals(UserType.MANAGER)) {
            Hotel hotel = getHotel(hotelId);
            hotel.setName(name);
            hotel.setDescription(descr);
            return hotel;
        }
        return null;
    }
}
