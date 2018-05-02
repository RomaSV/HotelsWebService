package web;

import api.HotelService;
import api.HotelUpdateRequest;
import api.RoomBookRequest;
import api.RoomUpdateRequest;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import hotels.Hotel;
import hotels.HotelNetwork;
import hotels.Room;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class HotelResource implements HotelService {

    private final HotelNetwork hotelNetwork = new HotelNetwork();

    public HotelResource() {
        //Init hotels from config
        Config config = ConfigFactory.load();
        for (long i = 1; i <= config.getObject("hotels").unwrapped().size(); i++) {
            Map<String, Object> hotel = config.getObject("hotels." + i).unwrapped();
            hotelNetwork.addHotel(Long.parseLong(hotel.get("adminId").toString()));
            hotelNetwork.updateHotel(i, hotel.get("name").toString(), hotel.get("description").toString());

            for (int j = 1; j <= config.getObject("hotels." + i + ".rooms").unwrapped().size(); j++) {
                Map<String, Object> roomConf = config.getObject("hotels." + i + ".rooms." + j).unwrapped();
                Room room = hotelNetwork.addRoom(i, roomConf.get("name").toString(),
                        roomConf.get("description").toString(), Double.parseDouble(roomConf.get("price").toString()));
                ArrayList<String> dates = (ArrayList<String>)roomConf.get("bookedDays");
                room.book(dates.get(0), dates.get(dates.size() - 1));
            }
        }
    }

    @Override
    public List<Hotel> getHotels() {
        return hotelNetwork.getHotels();
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

    @Override
    public Room updateRoom(long hotelId, int roomId, RoomUpdateRequest request) {
        return hotelNetwork.updateRoom(hotelId, roomId, request.getName(), request.getDescription(), request.getPrice());
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
