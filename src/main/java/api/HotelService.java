package api;

import hotels.Hotel;
import hotels.Room;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface HotelService {
    @RequestMapping(method = RequestMethod.GET, value = "/hotels")
    List<Hotel> getHotels();

    @RequestMapping(method = RequestMethod.GET, value = "/hotels/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") Long hotelId);

    @RequestMapping(method = RequestMethod.GET, value = "/hotels/{hotelId}/rooms")
    List<Room> getRooms(@PathVariable("hotelId") Long hotelId);

    @RequestMapping(method = RequestMethod.GET, value = "/hotels/{hotelId}/rooms/{roomId}")
    Room getRoom(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Integer roomId);

    @RequestMapping(method = RequestMethod.POST, value = "/hotels")
    Hotel addHotel(@RequestParam Long adminId);

    @RequestMapping(method = RequestMethod.POST, value = "/hotels/{hotelId}/rooms")
    Room addRoom(@PathVariable("hotelId") Long hotelId, RoomUpdateRequest request);

    @RequestMapping(method = RequestMethod.PUT, value = "/hotels/{hotelId}", consumes = "application/json")
    Hotel updateHotel(@PathVariable("hotelId") Long hotelId, HotelUpdateRequest request);

//    @RequestMapping(method = RequestMethod.PUT, value = "/hotels/{hotelId}/rooms/{roomId}")
//    Room updateRoom(@PathVariable("hotelId") long hotelId, @PathVariable("roomId") int roomId);

//    @RequestMapping(method = RequestMethod.DELETE, params = "/hotels")
//    boolean deleteHotels();
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/hotels/{hotelId}")
//    boolean deleteHotel(@PathVariable("hotelId") long hotelId);
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/hotels/{hotelId}/rooms")
//    boolean deleteRooms(@PathVariable("hotelId") long hotelId);
//
//    @RequestMapping(method = RequestMethod.DELETE, value = "/hotels/{hotelId}/rooms/{roomId}")
//    boolean deleteRoom(@PathVariable("hotelId") long hotelId, @PathVariable("roomId") int roomId);
}