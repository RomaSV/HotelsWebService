package hotels;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {
    private final int id;
    private List<LocalDate> bookedDays;

    private String name;
    private String description;
    private double pricePerNight;
    private int numberOfPeople;
    private boolean withBathroom;
    private boolean withFridge;

    public Room(int id) {
        this.id = id;
        bookedDays = new ArrayList<>();
    }

    public boolean book(String arrival, String departure) {
        LocalDate firstDay = LocalDate.parse(arrival);
        LocalDate lastDay = LocalDate.parse(departure);
        if (isFree(arrival, departure)) {
            for (LocalDate date = firstDay; date.isBefore(lastDay.plusDays(1)); date = date.plusDays(1)) {
                bookedDays.add(date);
            }
            return true;
        } else {
            return false;
        }
    }

    public void cancelBooking(String arrival, String  departure) {
        LocalDate firstDay = LocalDate.parse(arrival);
        LocalDate lastDay = LocalDate.parse(departure);
        checkDates(firstDay, lastDay);

        for (LocalDate date = firstDay; date.isBefore(lastDay.plusDays(1)); date = date.plusDays(1)) {
            if (bookedDays.contains(date)) {
                bookedDays.remove(date);
            }
        }
    }

    public boolean isFree(String arrival, String departure) {
        LocalDate firstDay = LocalDate.parse(arrival);
        LocalDate lastDay = LocalDate.parse(departure);
        checkDates(firstDay, lastDay);

        for (LocalDate date = firstDay; date.isBefore(lastDay.plusDays(1)); date = date.plusDays(1)) {
            if (bookedDays.contains(date)) {
                return false;
            }
        }
        return true;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    //Just for debug
    public String getBookedDays() {
        return bookedDays.toString();
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setWithBathroom(boolean withBathroom) {
        this.withBathroom = withBathroom;
    }

    public boolean isWithBathroom() {
        return withBathroom;
    }

    public void setWithFridge(boolean withFridge) {
        this.withFridge = withFridge;
    }

    public boolean isWithFridge() {
        return withFridge;
    }

    private void checkDates(LocalDate first, LocalDate second) {
        if (second.isBefore(first)) {
            throw new IllegalArgumentException("Invalid period.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}