package feign;

import api.HotelUpdateRequest;
import api.RoomBookRequest;
import api.RoomUpdateRequest;
import hotels.Hotel;
import hotels.HotelStatistics;
import hotels.NetworkStatistics;
import hotels.Room;

import java.util.List;
import java.util.Map;

public interface HotelsClient {
    @RequestLine("POST /hotels")
    @Headers("Content-Type: application/json")
    List<Hotel> getHotels(Map<String, String> params);

    @RequestLine("POST /hotels/statistics")
    NetworkStatistics getStatistics();

    @RequestLine("POST /hotels/{hotelId}")
    Hotel getHotel(@Param("hotelId") Long hotelId);

    @RequestLine("POST /hotels/{hotelId}/statistics")
    HotelStatistics getStatistics(@Param("hotelId") Long hotelId);

    @RequestLine("POST /hotels/{hotelId}/rooms")
    List<Room> getRooms(@Param("hotelId") Long hotelId, Map<String, String> params);

    @RequestLine("POST /hotels/{hotelId}/rooms/{roomId}")
    Room getRoom(@Param("hotelId") Long hotelId, @Param("roomId") Integer roomId);

    @RequestLine("POST /hotels/add")
    Hotel addHotel(String adminName);

    @RequestLine("POST /hotels/{hotelId}/rooms/add")
    Room addRoom(@Param("hotelId") Long hotelId, RoomUpdateRequest request);

    @RequestLine("PUT /hotels/{hotelId}")
    Hotel updateHotel(@Param("hotelId") Long hotelId, HotelUpdateRequest request);

    @RequestLine("PUT /hotels/{hotelId}/rooms/{roomId}")
    Room updateRoom(@Param("hotelId") long hotelId, @Param("roomId") int roomId, RoomUpdateRequest request);

    @RequestLine("PATCH /hotels/{hotelId}/rooms/{roomId}")
    Room bookRoom(@Param("hotelId") long hotelId, @Param("roomId") int roomId, RoomBookRequest request);

    @RequestLine("DELETE /hotels")
    void deleteHotels();

    @RequestLine("DELETE /hotels/{hotelId}")
    boolean deleteHotel(@Param("hotelId") long hotelId);

    @RequestLine("DELETE /hotels/{hotelId}/rooms")
    boolean deleteRooms(@Param("hotelId") long hotelId);

    @RequestLine("DELETE /hotels/{hotelId}/rooms/{roomId}")
    boolean deleteRoom(@Param("hotelId") long hotelId, @Param("roomId") int roomId);
}
