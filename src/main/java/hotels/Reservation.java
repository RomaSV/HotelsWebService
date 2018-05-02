package hotels;

public class Reservation {
    private final long id;
    private final long hotelId;
    private final int roomId;
    private final long clientId;
    private String arrival;
    private String departure;

    public Reservation(long id, long hotelId, int roomId, long clientId) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.clientId = clientId;
    }

    public long getId() {
        return id;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
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
