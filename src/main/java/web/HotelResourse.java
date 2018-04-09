package web;

import api.HotelService;
import api.HotelUpdateRequest;
import api.RoomUpdateRequest;
import hotels.Hotel;
import hotels.HotelNetwork;
import hotels.Room;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HotelResourse implements HotelService {

    private final HotelNetwork hotelNetwork = new HotelNetwork();

    @Override
    public List<Hotel> getHotels() {
        return hotelNetwork.getHotels();
    }

    @Override
    public Hotel getHotel(@PathVariable("hotelId") Long hotelId) {
        return hotelNetwork.getHotel(hotelId);
    }

    @Override
    public List<Room> getRooms(@PathVariable("hotelId") Long hotelId) {
        return hotelNetwork.getRooms(hotelId);
    }

    @Override
    public Room getRoom(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Integer roomId) {
        return hotelNetwork.getRoom(hotelId, roomId);
    }

    @Override
    public Hotel addHotel(Long adminId) {
        return hotelNetwork.addHotel(adminId);
    }

    @Override
    public Room addRoom(@PathVariable("hotelId") Long hotelId, @RequestBody RoomUpdateRequest request) {
        return hotelNetwork.
                addRoom(hotelId, request.getName(), request.getDescription(), request.getPrice());
    }

    @Override
    public Hotel updateHotel(@PathVariable("hotelId") Long hotelId, @RequestBody HotelUpdateRequest request) {
        return hotelNetwork.updateHotel(hotelId, request.getName(), request.getDescription());
    }
}
