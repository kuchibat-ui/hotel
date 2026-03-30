import model.Client;
import repository.DBconnect;
import service.BookingService;
import service.ClientService;
import service.RoomService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static RoomService roomService = new RoomService();
    static ClientService clientService = new ClientService();
    static BookingService bookingService = new BookingService();

    public static void main(String[] args) throws SQLException {

        while (true) {
            printMainMenu();
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    manageRooms();
                    break;
                case 2:
                    manageClients();
                    break;
                case 3:
                    manageBooking();
                    break;
                case 0:
                    return;
            }
        }
    }


    private static void printMainMenu() {
        System.out.println("\n===============================");
        System.out.println("    СИСТЕМА УПРАВЛЕНИЯ ОТЕЛЕМ");
        System.out.println("===============================");
        System.out.println("1. Управление номерами");
        System.out.println("2. Управление клиентами");
        System.out.println("3. Управление бронированием");
        System.out.println("4. Поиск");
        System.out.println("5. Статистика");
        System.out.println("0. Выход");
        System.out.println("===============================");
    }

    private static void manageRooms() throws SQLException {
        while (true) {
            System.out.println("==============================");
            System.out.println("\n--- УПРАВЛЕНИЕ НОМЕРАМИ ---");
            System.out.println("==============================");
            System.out.println("1. Показать все номера");
            System.out.println("2. Добавить номер");
            System.out.println("3. Удалить номер");
            System.out.println("4. Отредактировать номер");
            System.out.println("5. Найти комнату по номеру");
            System.out.println("0. НАЗАД");
            System.out.println("==============================");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    roomService.printAllRooms();
                    break;
                case 2:
                    roomService.addRoom();
                    break;

                case 3:
                    roomService.deleteRoom();
                    break;

                case 4:
                    roomService.updateRoom();
                    break;

                case 5:
                    roomService.searchbyNumberRoom();
                    break;

                case 0:
                    return;
                default:

            }
        }
    }


    private static void manageClients() throws SQLException {
        System.out.println("\n===============================");
        System.out.println("     УПРАВЛЕНИЯ КЛИЕНТАМИ");
        System.out.println("===============================");
        System.out.println("1. Добавить нового клиента");
        System.out.println("2. Найти клиента по паспорту");
        System.out.println("3. Найти клиента по ID");
        System.out.println("4. Вывести всех клиентов");
        System.out.println("5. Удалить клиента из базы ");
        System.out.println("5. Статистика");
        System.out.println("0. Выход");
        System.out.println("===============================");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                clientService.addClient();
                break;
            case 2:
                clientService.findByPass();
                break;
            case 3:
                clientService.findById();
                break;
            case 4:
                clientService.printToAllClients();
            case 5:
                manageDeleteClient();
                break;

            case 0:
                return;
            default:

        }
    }

    private static void manageDeleteClient() throws SQLException {
        System.out.println("1. Удалить клиента по ID ");
        System.out.println("2. Удалить клиента по фамилии ");
        System.out.println("0. НАЗАД");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                clientService.removeById();
                break;
            case 2:
                clientService.removeByLastname();
                break;
            case 0:
                return;
            default:

        }
    }

    /* todo НУЖНО ДОДЕЛАТЬ*/
    private static void manageBooking() throws SQLException {
        System.out.println("\n===============================");
        System.out.println("     УПРАВЛЕНИЯ БРОНИРОВАНИЯ ");
        System.out.println("===============================");
        System.out.println("1. Добавить бронирование");
        System.out.println("2. Найти бронирование");
        System.out.println("3. Найти бронирование");
        System.out.println("4. Вывести все бронирования");
        System.out.println("5. Удалить бронирование ");
        System.out.println("5. Статистика");
        System.out.println("0. Выход");
        System.out.println("===============================");


        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                bookingService.addBooking();

                break;
            case 2:
                bookingService.findBooking();
                break;
            case 0:
                return;
            default:

        }
    }

}