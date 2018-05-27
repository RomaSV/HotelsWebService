package web;

import api.*;
import hotels.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class HotelResource implements HotelService {

    private final HotelNetwork hotelNetwork;
    private final ConfigData configData;

    public HotelResource() {
        configData = new ConfigData();
        hotelNetwork = configData.getConfiguredHotelNetwork();
    }

    @Override
    public List<HotelData> getHotels(@RequestBody Map<String, String> params) {
        if (params.isEmpty()) {
            return getHotelsData(hotelNetwork.getHotels());
        } else {
            return getHotelsData(hotelNetwork.getHotels(params));
        }
    }

    @Override
    public NetworkStatistics getStatistics() {
        return configData.getNetworkStatistics();
    }

    @Override
    public HotelData getHotel(@PathVariable("hotelId") Long hotelId) {
        Hotel hotel = hotelNetwork.getHotel(hotelId);
        if (hotel == null) {
            throw new RuntimeException("Hotel with id \"" + hotelId + "\" is not found");
        }
        return getHotelData(hotel);
    }

    @Override
    public HotelStatistics getStatistics(@PathVariable("hotelId") Long hotelId) {
        return configData.getHotelStatistics(hotelId);
    }

    @Override
    public List<RoomData> getRooms(@PathVariable("hotelId") Long hotelId, @RequestBody Map<String, String> params) {
        return getRoomsData(hotelNetwork.getRooms(hotelId, params));
    }

    @Override
    public RoomData getRoom(@PathVariable("hotelId") Long hotelId, @PathVariable("roomId") Integer roomId) {
        return getRoomData(hotelNetwork.getRoom(hotelId, roomId));
    }

    @Override
    public HotelData addHotel(String adminName) {
        return getHotelData(hotelNetwork.addHotel(adminName));
    }

    @Override
    public RoomData addRoom(@PathVariable("hotelId") Long hotelId, @RequestBody RoomUpdateRequest request) {
        return getRoomData(hotelNetwork.addRoom(hotelId, request));
    }

    @Override
    public HotelData updateHotel(@PathVariable("hotelId") Long hotelId, @RequestBody HotelUpdateRequest request) {
        return getHotelData(hotelNetwork.updateHotel(hotelId, request));
    }

    @Override
    public RoomData updateRoom(long hotelId, int roomId, RoomUpdateRequest request) {
        return getRoomData(hotelNetwork.updateRoom(hotelId, roomId, request));
    }

    @Override
    public boolean bookRoom(long hotelId, int roomId, RoomBookRequest request) {
        Room room = hotelNetwork.getHotel(hotelId).getRoomById(roomId);
        return room.book(request.getArrival(), request.getDepartment());
    }

    @Override
    public void deleteHotels() {
        hotelNetwork.deleteHotels();
    }

    @Override
    public boolean deleteHotel(long hotelId) {
        return hotelNetwork.deleteHotel(hotelId);
    }

    @Override
    public boolean deleteRooms(long hotelId) {
        return hotelNetwork.deleteRooms(hotelId);
    }

    @Override
    public boolean deleteRoom(long hotelId, int roomId) {
        return hotelNetwork.deleteRoom(hotelId, roomId);
    }

    private List<HotelData> getHotelsData(List<Hotel> hotelList) {
        ArrayList<HotelData> hotels = new ArrayList<>();
        for (Hotel hotel: hotelList) {
            hotels.add(getHotelData(hotel));
        }
        return hotels;
    }

    private HotelData getHotelData(Hotel hotel) {
        HotelData hotelData = new HotelData();
        hotelData.setName(hotel.getName());
        hotelData.setDescription(hotel.getDescription());
        hotelData.setStars(hotel.getStars());
        hotelData.setCloseToCenter(hotel.isCloseToCenter());
        hotelData.setWithRestaurant(hotel.isWithRestaurant());
        hotelData.setRooms(getRoomsData(new ArrayList<>(hotel.getRooms().values())));
        hotelData.setAmountOfRooms(hotel.getAmountOfRooms());

        return hotelData;
    }

    private List<RoomData> getRoomsData(List<Room> roomList) {
        ArrayList<RoomData> rooms = new ArrayList<>();
        for (Room room: roomList) {
            rooms.add(getRoomData(room));
        }
        return rooms;
    }

    private RoomData getRoomData(Room room) {
        RoomData roomData = new RoomData();
        roomData.setName(room.getName());
        roomData.setDescription(room.getDescription());
        roomData.setPricePerNight(room.getPricePerNight());
        roomData.setNumberOfPeople(room.getNumberOfPeople());
        roomData.setWithBathroom(room.isWithBathroom());
        roomData.setWithFridge(room.isWithFridge());

        return roomData;
    }
}
