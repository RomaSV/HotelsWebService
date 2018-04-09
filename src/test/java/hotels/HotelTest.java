package hotels;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HotelTest {
    @Test
    public void addRoom() {
        Hotel hotel = new Hotel(1, 1);
        Room room = hotel.addRoom("2-х комнатный ЛЮКС", "", 1000.0);

        assertEquals(1, hotel.getAmountOfRooms());
        assertEquals("2-х комнатный ЛЮКС",
                room.getName());
    }

    @Test
    public void roomNotFoundTest() {
        Hotel hotel = new Hotel(1, 1);
        try {
            hotel.getRoomById(414);
            fail("Exception expected.");
        } catch (IllegalArgumentException e) {
            assertEquals("Room with id: 414 is not found.", e.getMessage());
        }
    }

    @Test
    public void deleteRoomTest() {
        Hotel hotel = new Hotel(1, 1);
        Room room = hotel.addRoom("Room", "", 42.0);

        assertTrue(hotel.deleteRoom(room.getId()));
        assertEquals(0, hotel.getAmountOfRooms());
    }
}
