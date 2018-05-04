package hotels;

import api.HotelUpdateRequest;
import api.RoomUpdateRequest;

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

    public List<Room> getRooms(long hotelId, Map <String, String> params) {
        String numberOfPeople = params.get("numberOfPeople");
        String hasBathroom = params.get("hasBathroom");
        String hasFridge = params.get("hasFridge");

        List<Room> result = new ArrayList<>();
        for (Room room: hotels.get(hotelId).getRooms().values()) {
            boolean accept = true;
            if (numberOfPeople != null && room.getNumberOfPeople() != Integer.parseInt(numberOfPeople)) {
                accept = false;
            }
            if (hasBathroom != null && room.isWithBathroom() != Boolean.parseBoolean(hasBathroom)) {
                accept = false;
            }
            if (hasFridge != null && room.isWithFridge() != Boolean.parseBoolean(hasFridge)) {
                accept = false;
            }

            if (accept) {
                result.add(room);
            }
        }
        return result;
    }

    public Room getRoom(long hotelId, int roomId) {
        return getHotel(hotelId).getRooms().getOrDefault(roomId, null);
    }

    public Hotel addHotel(String adminName) {
        Hotel hotel = new Hotel(hotelCounter.incrementAndGet(), adminName);
        hotels.put(hotel.getId(), hotel);
        return hotel;
    }

    public Room addRoom(long hotelId, RoomUpdateRequest request) {
        return getHotel(hotelId).addRoom(request);
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

    public Room updateRoom(long hotelId, int roomId, RoomUpdateRequest updateRequest) {
        Room room = getHotel(hotelId).getRoomById(roomId);
        room.setName(updateRequest.getName());
        room.setDescription(updateRequest.getDescription());
        room.setPricePerNight(updateRequest.getPrice());
        room.setNumberOfPeople(updateRequest.getNumberOfPeople());
        room.setWithBathroom(updateRequest.isWithBathroom());
        room.setWithFridge(updateRequest.isWithFridge());
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
