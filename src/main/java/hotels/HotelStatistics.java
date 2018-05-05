package hotels;

public class HotelStatistics {
    private long reservationNumber;
    private long mostPopularRoom;
    private double proceeds;

    public void setReservationNumber(long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public long getReservationNumber() {
        return reservationNumber;
    }

    public void setMostPopularRoom(long mostPopularRoom) {
        this.mostPopularRoom = mostPopularRoom;
    }

    public long getMostPopularRoom() {
        return mostPopularRoom;
    }

    public void setProceeds(double proceeds) {
        this.proceeds = proceeds;
    }

    public double getProceeds() {
        return proceeds;
    }
}
