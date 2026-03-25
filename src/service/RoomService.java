package service;

import model.Room;
import repository.RoomRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class RoomService {
    private RoomRepository roomRepository;

    public RoomService() {
        this.roomRepository = new RoomRepository();
    }

    public void addRoom() throws SQLException {
        System.out.println("СОЗДАНИЕ НОВОЙ КОМНАТЫ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер комнаты: ");
        String roomNumber = scanner.nextLine();
        boolean isPresent = roomRepository.findByRoomNumber(roomNumber);
        if (isPresent == true) {
            System.out.println("Ошибка: Комната с таким номером уже существует!");
        } else {
            System.out.println("Введите тип комнаты");
            String type = scanner.nextLine();
            System.out.println("Введите цену за ночь:");
            String price = scanner.nextLine();
            double pricePerNight = Double.parseDouble(price);
            Room room = new Room(roomNumber, type, pricePerNight);
            roomRepository.getRooms(room);
        }
    }


    public void printAllRooms() {
        System.out.println("показать все номера");
        roomRepository.showRooms();
    }


    public void deleteRoom() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID комнаты ");
        String id1 = scanner.nextLine();
        int id = Integer.parseInt(id1);
        roomRepository.delete(id);
    }

    public void updateRoom() throws SQLException {
        System.out.println("РЕДАКТИРОВАНИЕ КОМНАТЫ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер комнаты: ");
        String roomNumber = scanner.nextLine();
        boolean isPresent = roomRepository.findByRoomNumber(roomNumber);
        if (isPresent == true) {
            System.out.println("Сменить номер комнаты на: ");
            String num = scanner.nextLine();
            System.out.println("Введите тип комнаты");
            String type = scanner.nextLine();
            System.out.println("Введите цену за ночь:");
            String price = scanner.nextLine();
            double pricePerNight = Double.parseDouble(price);
            Room room = new Room(num, type, pricePerNight);
            roomRepository.getRooms(room);
            int number = Integer.parseInt(roomNumber);
            roomRepository.deleteByNumber(number);
        } else {
            System.out.println("Ошибка: Комната с таким номером НЕ существует!");

        }
    }
}
