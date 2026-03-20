package repository;

import model.Booking;
import model.Client;
import model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingRepository {


    List<Booking> bookings = new ArrayList<>();


    //создание нового бронирования
    public Optional<Booking> addBooking(Booking booking) {
        return bookings.stream()
                .filter((booking1) -> booking1.getCheckInDate().equals(booking.getCheckInDate())

    }
    //отмена бронирования
}
