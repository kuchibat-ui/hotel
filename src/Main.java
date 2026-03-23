import model.Client;
import repository.DBconnect;
import service.ClientService;
import service.RoomService;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static RoomService roomService = new RoomService();
    static ClientService clientService = new ClientService();

    public static void main(String[] args) {

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

    private static void manageRooms() {
        while (true) {
            System.out.println("\n--- УПРАВЛЕНИЕ НОМЕРАМИ ---");
            System.out.println("1. Показать все номера");
            System.out.println("2. Добавить номер");
            System.out.println("...");
            System.out.println("0. Назад");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    roomService.printAllRooms();
                    break;
                case 2:
                    roomService.addRoom("11", "11", 222);
                case 0:
                    return;
                default:
                    System.out.println("Такая функция ещё не реализована");
            }
        }
    }


    private static void manageClients() {
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
                clientService.save();
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
                System.out.println("Такая функция ещё не реализована");
        }
    }

    private static void manageDeleteClient() {
        System.out.println("1. Удалить клиента по ID ");
        System.out.println("2. Удалить клиента по фамилии ");
        int choice =scanner.nextInt();
        switch (choice){
            case 1:
                clientService.removeById();
            case 2:
                clientService.removeByLastname();
        }
    }


    private static void manageBooking() {
    }
}