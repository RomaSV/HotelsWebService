package api;

import hotels.Reservation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReservationService {
    @RequestMapping(method = RequestMethod.GET, value = "/reservations")
    List<Reservation> getReservations();

    @RequestMapping(method = RequestMethod.GET, value = "/reservations/{resId}")
    Reservation getReservationById(@PathVariable long resId);

    @RequestMapping(method = RequestMethod.GET, value = "/reservations")
    List<Reservation> getReservationsByHotel(@RequestParam long hotelId);

    @RequestMapping(method = RequestMethod.POST, value = "/reservations")
    Reservation addReservation(@RequestParam long hotelId, @RequestParam int roomId, @RequestParam long clientId);

    @RequestMapping(method = RequestMethod.PUT, value = "/reservations/{resId}")
    Reservation updateReservation(@PathVariable long resId, ReservationUpdateRequest request);
}
