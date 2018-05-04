package hotels;

import api.HotelUpdateRequest;

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

    public List<Hotel> getHotels(Map <String, String> params) {
        String stars = params.get("stars");
        String hasRestaurants = params.get("hasRestaurant");
        String closeToCenter = params.get("closeToCenter");

        List<Hotel> result = new ArrayList<>();
        for (Hotel hotel: hotels.values()) {
            boolean accept = true;
            if (stars != null && hotel.getStars() != Integer.parseInt(stars)) {
                accept = false;
            }
            if (hasRestaurants != null && hotel.isWithRestaurant() != Boolean.parseBoolean(hasRestaurants)) {
                accept = false;
            }
            if (closeToCenter != null && hotel.isCloseToCenter() != Boolean.parseBoolean(closeToCenter)) {
                accept = false;
            }

            if (accept) {
                result.add(hotel);
            }
        }
        return result;
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

    public Hotel addHotel(String adminName) {
        Hotel hotel = new Hotel(hotelCounter.incrementAndGet(), adminName);
        hotels.put(hotel.getId(), hotel);
        return hotel;
    }

    public Room addRoom(long hotelId, String name, String descr, double price) {
        return getHotel(hotelId).addRoom(name, descr, price);
    }

    public Hotel updateHotel(long hotelId, HotelUpdateRequest updateRequest) {
        Hotel hotel = getHotel(hotelId);
        hotel.setName(updateRequest.getName());
        hotel.setDescription(updateRequest.getDescription());
        hotel.setStars(updateRequest.getStars());
        hotel.setWithRestaurant(updateRequest.isWithRestaurant());
        hotel.setCloseToCenter(updateRequest.isCloseToCenter());
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
