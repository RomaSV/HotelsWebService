package hotels;

public class NetworkStatistics {
    private long reservationNumber;
    private long mostPopularHotel;
    private double proceeds;

    public void setReservationNumber(long reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public long getReservationNumber() {
        return reservationNumber;
    }

    public void setMostPopularHotel(long mostPopularHotel) {
        this.mostPopularHotel = mostPopularHotel;
    }

    public long getMostPopularHotel() {
        return mostPopularHotel;
    }

    public void setProceeds(double proceeds) {
        this.proceeds = proceeds;
    }

    public double getProceeds() {
        return proceeds;
    }
}
