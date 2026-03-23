package service;

import model.Client;
import model.Room;
import repository.ClientRepository;
import repository.DBconnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.Optional;
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
    public Client addClients(String lastname, String name, String passport, String email, String phone) {
        if (clientRepository.findByPassport(passport).isPresent()) {
            System.out.println("Ошибка: данный клиент уже есть в списке");
            return null;
        }
        Client client = new Client(lastname, name, email, passport, phone);
        return clientRepository.save(client);
    }

    /**
     * Метод добавляет нового клиента в БД
     */
    public void save() {
        String query = "INSERT INTO clients (lastname,name,email,passport,phone) VALUES (?,?,?,?,?)";
        try {
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

            Connection conn = DBconnect.getConnection();


            try (PreparedStatement ps = conn.prepareStatement(query)) {


                ps.setString(1, lastname);
                ps.setString(2, name);
                ps.setString(3, email);
                ps.setString(4, passport);
                ps.setString(5, phone);
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("клиент " + lastname + " " + name + " успешно добавлен в БД");
                } else {
                    System.out.println(" клиент не добавлен в БД");
                }

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    /**
     * Метод поиск клиента по паспорту
     */
    public void findByPassport() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("введите номер паспорта клиента для поиска ");
        String passport = scanner.nextLine();
        String query = "SELECT * FROM clients WHERE passport=? ";
        Connection conn = DBconnect.getConnection();

        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, passport);                  //подстановка параметра passport в запро1с
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String lastname = rs.getString("lastname");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String pass = rs.getString("passport");

            System.out.printf("\nID: %d,lastname: %s, name %s,  Email: %s, Passport: %s\n", id, lastname, name, email, pass);
        }

    }

}




