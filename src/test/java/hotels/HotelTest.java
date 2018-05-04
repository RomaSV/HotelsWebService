package hotels;

import api.RoomUpdateRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HotelTest {
    @Test
    public void addRoom() {
        Hotel hotel = new Hotel(1, "admin");
        RoomUpdateRequest request = new RoomUpdateRequest();
        request.setName("2-х комнатный ЛЮКС");
        request.setDescription("");
        request.setPrice(2000.0);
        Room room = hotel.addRoom(request);

        assertEquals(1, hotel.getAmountOfRooms());
        assertEquals("2-х комнатный ЛЮКС",
                room.getName());
    }

    @Test
    public void roomNotFoundTest() {
        Hotel hotel = new Hotel(1, "admin");
        try {
            hotel.getRoomById(414);
            fail("Exception expected.");
        } catch (IllegalArgumentException e) {
            assertEquals("Room with id: 414 is not found.", e.getMessage());
        }
    }

    @Test
    public void deleteRoomTest() {
        Hotel hotel = new Hotel(1, "admin");
        RoomUpdateRequest request = new RoomUpdateRequest();
        request.setName("room");
        request.setDescription("");
        request.setPrice(2000.0);
        Room room = hotel.addRoom(request);

        assertTrue(hotel.deleteRoom(room.getId()));
        assertEquals(0, hotel.getAmountOfRooms());
    }
}
