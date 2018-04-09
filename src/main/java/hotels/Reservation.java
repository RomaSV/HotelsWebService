package hotels;

import java.time.LocalDate;

public class Reservation {
    private final long id;
    private final long hotelId;
    private final int roomId;
    private final long clientId;
    private LocalDate arrival;
    private LocalDate departure;

    public Reservation(long id, long hotelId, int roomId, long clientId) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.clientId = clientId;
    }

    public long getId() {
        return id;
    }

    public LocalDate getArrival() {
        return arrival;
    }

    public void setArrival(LocalDate arrival) {
        this.arrival = arrival;
    }

    public LocalDate getDeparture() {
        return departure;
    }

    public void setDeparture(LocalDate departure) {
        this.departure = departure;
    }

    public long getHotelId() {
        return hotelId;
    }

    public int getRoomId() {
        return roomId;
    }

    public long getClientId() {
        return clientId;
    }
}
