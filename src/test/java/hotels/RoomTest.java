package hotels;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class RoomTest {
    private LocalDate first1 = LocalDate.of(2018, 4, 1);
    private LocalDate last1 = LocalDate.of(2018, 4, 10);
    private LocalDate first2 = LocalDate.of(2018, 4, 5);
    private LocalDate last2 = LocalDate.of(2018, 4, 15);

    @Test
    public void isFreeTest() {
        Room room = new Room(414);
        assertTrue(room.isFree(first1, last1));
    }

    @Test
    public void bookingTest() {
        Room room = new Room(414);
        assertTrue(room.book(first1, last1));
    }

    @Test
    public void conflictingDatesTest() {
        Room room = new Room(414);
        room.book(first1, last1);

        assertFalse(room.book(first2, last2));
    }

    @Test
    public void invalidDatesTest() {
        Room room = new Room(414);
        try {
            room.book(last1, first1);
            fail("Exception expected.");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid period.", e.getMessage());
        }
    }

    @Test
    public void cancelBookingTest() {
        Room room = new Room(414);

        room.book(first1, last1);
        assertFalse(room.isFree(first1, last1));

        room.cancelBooking(first1, last2);
        assertTrue(room.isFree(first1, first2));
    }
}
