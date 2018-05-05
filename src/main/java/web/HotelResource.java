package web;

import api.HotelService;
import api.HotelUpdateRequest;
import api.RoomBookRequest;
import api.RoomUpdateRequest;
import hotels.Hotel;
import hotels.HotelNetwork;
import hotels.NetworkStatistics;
import hotels.Room;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HotelResource implements HotelService {

    private final HotelNetwork hotelNetwork;
    private final ConfigData configData;

    public HotelResource() {
        configData = new ConfigData();
        hotelNetwork = configData.getConfiguredHotelNetwork();
    }

    @Override
    public List<Hotel> getHotels(@RequestParam Map<String, String> params) {
        if (params.isEmpty()) {
            return hotelNetwork.getHotels();
        } else {
            return hotelNetwork.getHotels(params);
        }
    }

    @Override
    public NetworkStatistics getStatistics() {
        return configData.getNetworkStatistics();
    }

    @Override
    public Hotel getHotel(@PathVariable("hotelId") Long hotelId) {
        Hotel hotel = hotelNetwork.getHotel(hotelId);
        if (hotel == null) {
            throw new RuntimeException("Hotel with id \"" + hotelId + "\" is not found");
        }
        return hotel;
    }

    @Override
    public List<Room> getRooms(@PathVariable("hotelId") Long hotelId, @RequestParam Map<String, String> params) {
        return hotelNetwork.getRooms(hotelId, params);
    }

    @Override
    public Room getRoom(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Integer roomId) {
        return hotelNetwork.getRoom(hotelId, roomId);
    }

    @Override
    public Hotel addHotel(String adminName) {
        return hotelNetwork.addHotel(adminName);
    }

    @Override
    public Room addRoom(@PathVariable("hotelId") Long hotelId, @RequestBody RoomUpdateRequest request) {
        return hotelNetwork.
                addRoom(hotelId, request);
    }

    @Override
    public Hotel updateHotel(@PathVariable("hotelId") Long hotelId, @RequestBody HotelUpdateRequest request) {
        return hotelNetwork.updateHotel(hotelId, request);
    }

    @Override
    public Room updateRoom(long hotelId, int roomId, RoomUpdateRequest request) {
        return hotelNetwork.updateRoom(hotelId, roomId, request);
    }

    @Override
    public Room bookRoom(long hotelId, int roomId, RoomBookRequest request) {
        Room room = hotelNetwork.getHotel(hotelId).getRoomById(roomId);
        room.book(request.getArrival(), request.getDepartment());
        return room;
    }

    @Override
    public void deleteHotels() {
        hotelNetwork.deleteHotels();
    }

    @Override
    public boolean deleteHotel(long hotelId) {
        return hotelNetwork.deleteHotel(hotelId);
    }

    @Override
    public boolean deleteRooms(long hotelId) {
        return hotelNetwork.deleteRooms(hotelId);
    }

    @Override
    public boolean deleteRoom(long hotelId, int roomId) {
        return hotelNetwork.deleteRoom(hotelId, roomId);
    }
}
