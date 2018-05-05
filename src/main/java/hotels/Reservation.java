package hotels;

public class Reservation {
    private final long id;
    private final long hotelId;
    private final int roomId;
    private final String clientName;
    private String arrival;
    private String departure;
    private int prepay;

    public Reservation(long id, long hotelId, int roomId, String clientName) {
        this.id = id;
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.clientName = clientName;
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

    public int getPrepay() {
        return prepay;
    }

    public void setPrepay(int prepay) {
        this.prepay = prepay;
    }

    public long getHotelId() {
        return hotelId;
    }

    public int getRoomId() {
        return roomId;
    }

    public String getClientName() {
        return clientName;
    }
}
