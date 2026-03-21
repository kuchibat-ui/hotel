package service;

import model.Client;
import model.Room;
import repository.BookingRepository;
import repository.ClientRepository;
import repository.RoomRepository;

public class BookingService {

    private BookingRepository bookingRepository;
    private RoomRepository roomRepository;
    private ClientRepository clientRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    //заселение чувака в номер
    public boolean checkInRoom(Client client, Room room){

        return false;
    }
}



