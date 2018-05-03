package hotels;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Hotel {
    private AtomicInteger roomCounter;
    private final long id;
    private String name;
    private String description;
    private final Map<Integer, Room> rooms;
    private String adminName;

    public Hotel(long id, String adminName) {
        this.id = id;
        this.adminName = adminName;
        rooms = new HashMap<>();
        roomCounter = new AtomicInteger();
    }

    public Room addRoom(String name, String description, double price) {
        Room room = new Room(roomCounter.incrementAndGet());
        room.setName(name);
        room.setDescription(description);
        room.setPricePerNight(price);
        rooms.put(room.getId(), room);
        return room;
    }

    public boolean deleteRoom(int id) {
        if (rooms.containsKey(id)) {
            rooms.remove(id);
            return true;
        } else {
            return false;
        }
    }

    public Room getRoomById(int id) {
        if (rooms.containsKey(id)) {
            return rooms.get(id);
        } else {
            throw new IllegalArgumentException("Room with id: " + id + " is not found.");
        }
    }

    public long getId() {
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

    public Map<Integer, Room> getRooms() {
        return rooms;
    }

    public int getAmountOfRooms() {
        return rooms.size();
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminName() {
        return adminName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return id == hotel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}