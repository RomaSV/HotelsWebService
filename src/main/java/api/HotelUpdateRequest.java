package api;

public class HotelUpdateRequest {
    private String name;
    private String description;
    private int stars;
    private boolean withRestaurant;
    private boolean closeToCenter;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setStars(int stars) {
        this.stars = stars;
    }

    public int getStars() {
        return stars;
    }

    public void setWithRestaurant(boolean withRestaurant) {
        this.withRestaurant = withRestaurant;
    }

    public boolean isWithRestaurant() {
        return withRestaurant;
    }

    public void setCloseToCenter(boolean closeToCenter) {
        this.closeToCenter = closeToCenter;
    }

    public boolean isCloseToCenter() {
        return closeToCenter;
    }
}
