package service;

import model.Client;
import model.Room;
import repository.BookingRepository;
import repository.ClientRepository;
import repository.RoomRepository;

import java.time.LocalDate;
import java.util.Optional;

public class BookingService {

    private BookingRepository bookingRepository = new BookingRepository();
    private RoomRepository roomRepository = new RoomRepository();
    private ClientRepository clientRepository = new ClientRepository();

    public BookingService(BookingRepository bookingRepository, ClientRepository clientRepository, RoomRepository roomRepository) {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.roomRepository = roomRepository;
    }

    // добавление бронирования/
    // * todo НУЖНО ДОДЕЛАТЬ, НЕ ЗНАЮ КАК */
    public boolean checkInRoom(Client client, Room room, LocalDate checkInDate, LocalDate checkOutDate){
        if (client == null || room == null || checkInDate == null || checkOutDate == null){
            return false;
            /* todo НУЖНО ДОДЕЛАТЬ, НЕ ЗНАЮ КАК , ВОЗМОЖНО EXISTS */

        }

        return false;

    }
}



