package api;

public class RoomUpdateRequest {
    private String name;
    private String description;
    private Double price;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public boolean isWithFridge() {
        return withFridge;
    }

    public void setWithFridge(boolean withFridge) {
        this.withFridge = withFridge;
    }

    public boolean isWithBathroom() {
        return withBathroom;
    }

    public void setWithBathroom(boolean withBathroom) {
        this.withBathroom = withBathroom;
    }
}
