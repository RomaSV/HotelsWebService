package api;

import java.util.List;

public class HotelData {
    private String name;
    private String description;
    private int stars;
    private int amountOfRooms;
    private boolean withRestaurant;
    private boolean closeToCenter;
    private List<RoomData> rooms;

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

    public int getStars() {
        return stars;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public boolean isWithRestaurant() {
        return withRestaurant;
    }

    public void setWithRestaurant(boolean withRestaurant) {
        this.withRestaurant = withRestaurant;
    }

    public boolean isCloseToCenter() {
        return closeToCenter;
    }

    public void setCloseToCenter(boolean closeToCenter) {
        this.closeToCenter = closeToCenter;
    }

    public List<RoomData> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomData> rooms) {
        this.rooms = rooms;
    }

    public int getAmountOfRooms() {
        return amountOfRooms;
    }

    public void setAmountOfRooms(int amountOfRooms) {
        this.amountOfRooms = amountOfRooms;
    }
}
