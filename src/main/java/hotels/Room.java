package hotels;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {
    private final int id;
    private String name;
    private String description;
    private double pricePerNight;
    private List<LocalDate> bookedDays;

    public Room(int id) {
        this.id = id;
        bookedDays = new ArrayList<>();
    }

    public boolean book(LocalDate firstDay, LocalDate lastDay) {
        if (isFree(firstDay, lastDay)) {
            for (LocalDate date = firstDay; date.isBefore(lastDay); date = date.plusDays(1)) {
                bookedDays.add(date);
            }
            return true;
        } else {
            return false;
        }
    }

    public void cancelBooking(LocalDate firstDay, LocalDate lastDay) {
        checkDates(firstDay, lastDay);

        for (LocalDate date = firstDay; date.isBefore(lastDay); date = date.plusDays(1)) {
            if (bookedDays.contains(date)) {
                bookedDays.remove(date);
            }
        }
    }

    public boolean isFree(LocalDate firstDay, LocalDate lastDay) {
        checkDates(firstDay, lastDay);

        for (LocalDate date = firstDay; date.isBefore(lastDay); date = date.plusDays(1)) {
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