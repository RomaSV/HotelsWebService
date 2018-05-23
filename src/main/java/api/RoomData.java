package api;

public class RoomData {
    private String name;
    private String description;
    private double pricePerNight;
    private int numberOfPeople;
    private boolean withBathroom;
    private boolean withFridge;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public boolean isWithBathroom() {
        return withBathroom;
    }

    public void setWithBathroom(boolean withBathroom) {
        this.withBathroom = withBathroom;
    }

    public boolean isWithFridge() {
        return withFridge;
    }

    public void setWithFridge(boolean withFridge) {
        this.withFridge = withFridge;
    }
}
