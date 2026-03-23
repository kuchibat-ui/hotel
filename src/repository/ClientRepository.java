package repository;

//ТОЛЬКО ДЛЯ БАЗЫ ДАННЫХ
//НЕ ДОЛЖЕН СОДЕРЖАТЬ БИЗНЕС ЛОГИКУ
// НЕ ДОЛЖЕН ВЫЗЫВАТЬ CLIENTSERVICE
// ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ

import model.Client;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static repository.DBconnect.getConnection;

public class ClientRepository {
    List<Client> clients = new ArrayList<>();

    /**
     * метод добавляет клиента  в БД     *
     *
     * @param client
     * @return
     */
    public Client saveRepo(Client client) {
        String query = "INSERT INTO clients (lastname,name,email,passport,phone) VALUES (?,?,?,?,?)";
        try {
            Connection conn = getConnection();
            try (PreparedStatement ps = conn.prepareStatement(query)) {

                ps.setString(1, client.getLastname());
                ps.setString(2, client.getName());
                ps.setString(3, client.getEmail());
                ps.setString(4, client.getPassport());
                ps.setString(5, client.getPhone());
                int rows = ps.executeUpdate();
                if (rows > 0) {
                    System.out.println("клиент " + client.getLastname() + " " + client.getName() + " успешно добавлен в БД");

                } else {
                    System.out.println(" клиент не добавлен в БД");
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        clients.add(client);
        return client;
    }


    public void deleteClient(Client client) {
        clients.remove(client);
    }

    // поиск чувака по id
    public void findById(int id) throws SQLException {
        String query = "SELECT * FROM clients WHERE id=? ";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);                  //подстановка параметра passport в запро1с
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                String lastname = rs.getString("lastname");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String pass = rs.getString("passport");
                System.out.printf("\nlastname: %s, name %s,  Email: %s, Passport: %s\n", lastname, name, email, pass);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // поиск чуваков по имени
    public Optional<Client> findByLastname(String namePart) {
        return clients.stream()
                .filter(client -> client.getLastname().toLowerCase().contains(namePart.toLowerCase()))
                .findFirst();
    }

    ;

    // поиск чувака по телефону
    public Optional<Client> findBuPhone(String phone) throws SQLException {

        String query = "SELECT * FROM clients WHILE phone=? ";
        Connection conn = getConnection();
        Statement st = conn.createStatement();
        PreparedStatement ps = conn.prepareStatement("clients");
        ps.setString(1, phone);                  //подстановка параметра phone в запрос
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id");
            String lastname = rs.getString("lastname");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String pass = rs.getString("passport");

            System.out.printf("\nID: %d,lastname: %s, name %s,  Email: %s, Passport: %s\n", id, lastname, name, email, pass);
        }


        return clients.stream()
                .filter(client -> client.getPhone().equals(phone))
                .findFirst();
    }

    /**
     * поиск чувака по паспорту
     */
    public void findByPass(String passport) throws SQLException {
        String query = "SELECT * FROM clients WHERE passport=? ";
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // удаляет чувака по id
    public void delete(int id) {
        String query = "DELETE FROM clients WHERE id = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);                  //подстановка параметра passport в запро1с
            ps.executeUpdate();
            System.out.println("удалена строка " + id);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //вывести весь список clients
    public ArrayList<Client> getAllClients() {
        return new ArrayList<>(clients);
    }


    /**
     * получить все данные клиентов из базы данных     *
     *
     * @return List
     */
    public void findAll() throws SQLException {
        String query = "SELECT * FROM clients";
        try {
            Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String lastname = rs.getString("lastname");
                String name = rs.getString("name");
                System.out.printf("\n lastname: %s, name: %s\n", lastname, name);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public void deleteByLastname(String lastname) {
        String query = "DELETE FROM clients WHERE lastname = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, lastname);                  //подстановка параметра passport в запро1с
            ps.executeUpdate();
            System.out.println("удален клиент из базы данных " + lastname);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //вывести весь список clients
    public ArrayList<Client> getAllClient() {
        return new ArrayList<>(clients);
    }


//    public boolean update(Client updateClient) throws SQLException {
//        Optional<Client> existingClient = findById(updateClient.getId()); // вызов findById() найти Client с таким же id в clients
//        if (existingClient.isPresent()) {                           //если Client с таким id существует, то
//            Client client = existingClient.get();                   // извлекаем этот Client из clients
//            client.setName(updateClient.getName());                 // обновляем поля Name
//            client.setPassport(updateClient.getPassport());         // обновляем поля passport
//            client.setPhone(updateClient.getPhone());               // обновляем поля phone
//            client.setEmail(updateClient.getEmail());               // обновляем поле email
//            return true;                                            // возвращаем true при успешном обновлении
//        }
//        return false;                                               // возвращаем false при неудачном
//    }
//
}
