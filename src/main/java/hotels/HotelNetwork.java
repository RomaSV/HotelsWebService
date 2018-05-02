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
        Hotel hotel = new Hotel(hotelCounter.incrementAndGet(), adminId);
        hotels.put(hotel.getId(), hotel);
        return hotel;
    }

    public Room addRoom(long hotelId, String name, String descr, double price) {
        return getHotel(hotelId).addRoom(name, descr, price);
    }

    public Hotel updateHotel(long hotelId, String name, String descr) {
        Hotel hotel = getHotel(hotelId);
        hotel.setName(name);
        hotel.setDescription(descr);
        return hotel;
    }

    public Room updateRoom(long hotelId, int roomId, String name, String description, double price) {
        Room room = getHotel(hotelId).getRoomById(roomId);
        room.setName(name);
        room.setDescription(description);
        room.setPricePerNight(price);
        return room;
    }

    public void deleteHotels() {
        hotels.clear();
    }

    /**
     * Delete a particular hotel.
     * @param hotelId - id of the hotel.
     * @return true if it succeeds and false if the hotel is not found.
     */
    public boolean deleteHotel(long hotelId) {
        if (hotels.containsKey(hotelId)) {
            hotels.remove(hotelId);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete rooms of a particular hotel.
     * @param hotelId - id of the hotel.
     * @return true if it succeeds and false if the hotel is not found.
     */
    public boolean deleteRooms(long hotelId) {
        if (hotels.containsKey(hotelId)) {
            hotels.get(hotelId).getRooms().clear();
            return true;
        } else {
            return false;
        }
    }

    /**
     * Delete a particular room of the hotel.
     * @param hotelId - id of the hotel.
     * @param roomId - id of the room.
     * @return true if it succeeds and false if the hotel or room is not found.
     */
    public boolean deleteRoom(long hotelId, int roomId) {
        Hotel hotel = getHotel(hotelId);
        if (hotel != null && hotel.getRooms().containsKey(roomId)) {
            hotel.getRooms().remove(roomId);
            return true;
        } else {
            return false;
        }
    }
}
