package service;

import model.Booking;
import model.Client;
import model.Room;
import repository.BookingRepository;
import repository.ClientRepository;
import repository.RoomRepository;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.Scanner;

public class BookingService {

    private BookingRepository bookingRepository = new BookingRepository();
    private RoomRepository roomRepository = new RoomRepository();
    private ClientRepository clientRepository = new ClientRepository();

    public BookingService() {
        this.bookingRepository = bookingRepository;
        this.clientRepository = clientRepository;
        this.roomRepository = roomRepository;
    }
public static   int count;

    // добавление бронирования/
    public void addBooking() throws SQLException {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID клиента");
        String idClient = scanner.nextLine();
        int clientId = Integer.parseInt(idClient);
       Client client = clientRepository.findById(clientId);

        System.out.println("Введите номер комнаты ");
        String num = scanner.nextLine();
       Room room =  roomRepository.searchByNumber(num);

        System.out.println("Выберите дату заезда");
        LocalDate inData = LocalDate.parse(scanner.nextLine());

        System.out.println("Выберите дату выезда");
        LocalDate outData = LocalDate.parse(scanner.nextLine());

        checkInRoom(client,room,inData,outData);

    }
    // * todo НУЖНО ДОДЕЛАТЬ, НЕ ЗНАЮ КАК */
    public boolean checkInRoom(Client client, Room room, LocalDate checkInDate, LocalDate checkOutDate) throws SQLException {
        if (client == null || room == null || checkInDate == null || checkOutDate == null) {
            System.out.println("1");
            return false;
        }
        if (checkInDate.isAfter(checkOutDate) || checkInDate.isBefore(LocalDate.now())){
            System.out.println("2");
            return false;
        }

        boolean existingClient = clientRepository.searchById(client.getId());
        if (!existingClient){
            System.out.println("3");
            return false;
        }

        boolean existingRoom = roomRepository.findById(room.getId());
        if (!existingRoom){
            System.out.println("4");
            return false;
        }

        if (bookingRepository.isRoomOccupied(room.getId(),checkInDate,checkOutDate)){
            System.out.println("ошибка: комната на эти дни забронирована");
            return  false;
        }

        Booking booking = new Booking(
                client,
                room,
                checkInDate,
                checkOutDate,
                count++
        );

        bookingRepository.save(booking);
        return true;
    }
}



