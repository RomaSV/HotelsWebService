package api;

import hotels.Hotel;
import hotels.Room;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public interface HotelService {
    @RequestMapping(method = RequestMethod.GET, value = "/hotels")
    List<Hotel> getHotels(@RequestParam Map<String, String> params);

    @RequestMapping(method = RequestMethod.GET, value = "/hotels/{hotelId}")
    Hotel getHotel(@PathVariable("hotelId") Long hotelId);

    @RequestMapping(method = RequestMethod.GET, value = "/hotels/{hotelId}/rooms")
    List<Room> getRooms(@PathVariable("hotelId") Long hotelId);

    @RequestMapping(method = RequestMethod.GET, value = "/hotels/{hotelId}/rooms/{roomId}")
    Room getRoom(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Integer roomId);

    @RequestMapping(method = RequestMethod.POST, value = "/hotels")
    @Secured("ROLE_MANAGER")
    @ApiOperation(value = "Add hotel", authorizations = {@Authorization(value="basicAuth")})
    Hotel addHotel(@RequestParam String adminName);

    @RequestMapping(method = RequestMethod.POST, value = "/hotels/{hotelId}/rooms")
    @Secured("ROLE_MANAGER")
    @ApiOperation(value = "Add room", authorizations = {@Authorization(value="basicAuth")})
    Room addRoom(@PathVariable("hotelId") Long hotelId, RoomUpdateRequest request);

    @RequestMapping(method = RequestMethod.PUT, value = "/hotels/{hotelId}", consumes = "application/json")
    @Secured("ROLE_MANAGER")
    @ApiOperation(value = "Update hotel info", authorizations = {@Authorization(value="basicAuth")})
    Hotel updateHotel(@PathVariable("hotelId") Long hotelId, HotelUpdateRequest request);

    @RequestMapping(method = RequestMethod.PUT, value = "/hotels/{hotelId}/rooms/{roomId}")
    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @ApiOperation(value = "Update room info", authorizations = {@Authorization(value="basicAuth")})
    Room updateRoom(@PathVariable("hotelId") long hotelId, @PathVariable("roomId") int roomId, RoomUpdateRequest request);

    @RequestMapping(method = RequestMethod.PATCH, value = "/hotels/{hotelId}/rooms/{roomId}")
    Room bookRoom(@PathVariable("hotelId") long hotelId, @PathVariable("roomId") int roomId, RoomBookRequest request);

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotels")
    @Secured("ROLE_MANAGER")
    @ApiOperation(value = "Delete all the hotels", authorizations = {@Authorization(value="basicAuth")})
    void deleteHotels();

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotels/{hotelId}")
    @Secured("ROLE_MANAGER")
    @ApiOperation(value = "Delete a particular hotel", authorizations = {@Authorization(value="basicAuth")})
    boolean deleteHotel(@PathVariable("hotelId") long hotelId);

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotels/{hotelId}/rooms")
    @Secured("ROLE_MANAGER")
    @ApiOperation(value = "Delete all the rooms in the hotel", authorizations = {@Authorization(value="basicAuth")})
    boolean deleteRooms(@PathVariable("hotelId") long hotelId);

    @RequestMapping(method = RequestMethod.DELETE, value = "/hotels/{hotelId}/rooms/{roomId}")
    @Secured("ROLE_MANAGER")
    @ApiOperation(value = "Delete a particular room in the hotel", authorizations = {@Authorization(value="basicAuth")})
    boolean deleteRoom(@PathVariable("hotelId") long hotelId, @PathVariable("roomId") int roomId);
}