package api;

import hotels.Hotel;
import hotels.HotelStatistics;
import hotels.NetworkStatistics;
import hotels.Room;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public interface HotelService {
    @RequestMapping(method = RequestMethod.POST, value = "/hotels",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all the hotels")
    List<HotelData> getHotels(@RequestBody Map<String, String> params);

    @RequestMapping(method = RequestMethod.POST, value = "/hotels/statistics",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_MANAGER")
    @ApiOperation(value = "Get statistics", authorizations = {@Authorization(value="basicAuth")})
    NetworkStatistics getStatistics();

    @RequestMapping(method = RequestMethod.POST, value = "/hotels/{hotelId}",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a particular hotel")
    Hotel getHotel(@PathVariable("hotelId") Long hotelId);

    @RequestMapping(method = RequestMethod.POST, value = "/hotels/{hotelId}/statistics",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({"ROLE_MANAGER", "ROLE_ADMINISTRATOR"})
    @ApiOperation(value = "Get statistics", authorizations = {@Authorization(value="basicAuth")})
    HotelStatistics getStatistics(@PathVariable("hotelId") Long hotelId);

    @RequestMapping(method = RequestMethod.POST, value = "/hotels/{hotelId}/rooms",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all the rooms in the hotel")
    List<Room> getRooms(@PathVariable("hotelId") Long hotelId, @RequestBody Map<String, String> params);

    @RequestMapping(method = RequestMethod.POST, value = "/hotels/{hotelId}/rooms/{roomId}",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get a particular room in the hotel")
    Room getRoom(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Integer roomId);

    @RequestMapping(method = RequestMethod.POST, value = "/hotels/add",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_MANAGER")
    @ApiOperation(value = "Add hotel", authorizations = {@Authorization(value="basicAuth")})
    Hotel addHotel(@RequestParam String adminName);

    @RequestMapping(method = RequestMethod.POST, value = "/hotels/{hotelId}/rooms/add",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_MANAGER")
    @ApiOperation(value = "Add room", authorizations = {@Authorization(value="basicAuth")})
    Room addRoom(@PathVariable("hotelId") Long hotelId, RoomUpdateRequest request);

    @RequestMapping(method = RequestMethod.PUT, value = "/hotels/{hotelId}",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured("ROLE_MANAGER")
    @ApiOperation(value = "Update hotel info", authorizations = {@Authorization(value="basicAuth")})
    Hotel updateHotel(@PathVariable("hotelId") Long hotelId, HotelUpdateRequest request);

    @RequestMapping(method = RequestMethod.PUT, value = "/hotels/{hotelId}/rooms/{roomId}",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @Secured({"ROLE_MANAGER", "ROLE_ADMIN"})
    @ApiOperation(value = "Update room info", authorizations = {@Authorization(value="basicAuth")})
    Room updateRoom(@PathVariable("hotelId") long hotelId, @PathVariable("roomId") int roomId, RoomUpdateRequest request);

    @RequestMapping(method = RequestMethod.PATCH, value = "/hotels/{hotelId}/rooms/{roomId}",
                    consumes = MediaType.APPLICATION_JSON_VALUE,
                    produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Make a reservation in the room. Returns true if succeeds.")
    boolean bookRoom(@PathVariable("hotelId") long hotelId, @PathVariable("roomId") int roomId, RoomBookRequest request);

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