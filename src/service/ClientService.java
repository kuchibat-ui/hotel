package service;
// ТОЛЬКО ДЛЯ БИЗНЕС ЛОГИКИ
// ЗАВИСИМОСТИ ПЕРЕДАЮТСЯ ЧЕРЕЗ КОНСТРУКТОРЫ
// НЕ ДОЛЖЕН СОДЕРЖАТЬ SQL
// ВЫЗЫВАЕТ REPOSITORY А НЕ НАОБОРОТ
// ПОЛУАЕТ ОТ MAIN ЗАПРОСЫ
// ВЗАИМОДЕЙСТВИЕ С ПОЛЬЗОВАТЕЛЕМ


import model.Client;
import repository.ClientRepository;

import java.sql.SQLException;
import java.util.Scanner;

public class ClientService {
    ClientRepository clientRepository = new ClientRepository();

    public ClientService() {
        this.clientRepository = clientRepository;

    }

    // добавление клиента , редактирование клиента, все что касается данных о клиенте

    /**
     * метод добавляет клиента , проверяет его на уникальность по паспорту
     * если он уникальный то добавляет его в БД     *
     *
     * @param lastname
     * @param name
     * @param passport
     * @param email
     * @param phone
     * @return
     */
//    public void  addClients(String lastname, String name, String passport, String email, String phone) {
//        if (clientRepository.findByPass(passport).isPresent()) {
//            System.out.println("Ошибка: данный клиент уже есть в списке");
//            return null;
//        }
//        Client client = new Client(lastname, name, email, passport, phone);
//        return clientRepository.save(client);
//    }


    /**
     * Метод добавляет нового клиента
     */
    public void save() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите имя:");
        String name = scanner.nextLine();
        System.out.println("введите фамилию:");
        String lastname = scanner.nextLine();
        System.out.println("введите емайл:");
        String email = scanner.nextLine();
        System.out.println("введите паспорт:");
        String passport = scanner.nextLine();
        System.out.println("введите телефон:");
        String phone = scanner.nextLine();
        Client client = new Client(lastname, name, email, passport, phone);
        clientRepository.saveRepo(client);
    }

    /**
     * метод по поиску клиента по паспорту
     */
    public void findByPass(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер паспорта клиента: ");
        String passport = scanner.nextLine();
            clientRepository.findByPass(passport);
    }


    /**
     * метод для поиска клиента по id
     */
    public void findById() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер клиента: ");
        String idClient = scanner.nextLine();
        int id = Integer.parseInt(idClient) ;
            clientRepository.findById(id);

    }

    /**
     * метод для вывода всех клиентов из базы данных
     */
    public void printToAllClients(){
        try {
            clientRepository.findAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * удаление клиента по ID
     */
    public void removeById() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите ID клиента ");
        String id1 =scanner.nextLine();
        int id = Integer.parseInt(id1);
        clientRepository.delete(id);
    }

    /**
     * удаление клиента по Фамилии
     */
    public void removeByLastname() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите фамилию ");
        String lastName = scanner.nextLine();
        clientRepository.deleteByLastname(lastName);

    }

}
