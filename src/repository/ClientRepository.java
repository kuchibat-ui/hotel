package repository;

import com.mysql.cj.conf.DatabaseUrlContainer;
import model.Client;
import model.Room;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ClientRepository {
    List<Client> clients = new ArrayList<>();

    /**
     * метод добавляет клиента в базу данных и в List     *
     *
     * @param client
     * @return
     */
    public Client save(Client client) {
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
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, client.getLastname());
            ps.setString(2, client.getName());
            ps.setString(3, client.getEmail());
            ps.setString(4, client.getPassport());
            ps.setString(5, client.getPhone());
            ps.executeUpdate();
            clients.add(client);
            return client;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void deleteClient(Client client) {
        clients.remove(client);
    }

    // поиск чувака по id
    public Optional<Client> findById(int id) {
        return clients.stream()
                .filter(client -> client.getId() == id)
                .findFirst();
    }

    // поиск чуваков по имени
    public Optional<Client> findByLastname(String namePart) {
        return clients.stream()
                .filter(client -> client.getLastname().toLowerCase().contains(namePart.toLowerCase()))
                .findFirst();
    }

    ;

    // поиск чувака по телефону
    public Optional<Client> findBuPhone(String phone) {
        return clients.stream()
                .filter(client -> client.getPhone().equals(phone))
                .findFirst();
    }

    //поиск чувака по паспорту
    public Optional<Client> findBuPassport(String passport) {
        return clients.stream()
                .filter(client -> client.getPassport().equals(passport))
                .findFirst();
    }


    // удаляет чувака по id
    public boolean delete(int id) {
        return clients.removeIf(client -> client.getId() == id);
    }

    //вывести весь список clients
    public List<Client> getAllClients() {

        return new ArrayList<>(clients);
    }

    /**
     * получить все данные клиентов из базы данных     *
     * @return List
     */
    public List<Client> findAll() throws SQLException {
        String query = "SELECT * FROM clients";
        try {
            Connection conn = DBconnect.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                String lastname = rs.getString("lastname");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String passport = rs.getString("passport");
                System.out.printf("\n lastname: %s, name: %s, Email: %s,Phone: %s, Passport: %s\n", lastname, name, email, phone, passport);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ArrayList<>(clients);
    }

    public boolean update(Client updateClient) {
        Optional<Client> existingClient = findById(updateClient.getId()); // вызов findById() найти Client с таким же id в clients
        if (existingClient.isPresent()) {                           //если Client с таким id существует, то
            Client client = existingClient.get();                   // извлекаем этот Client из clients
            client.setName(updateClient.getName());         // обновляем поля Name
            client.setPassport(updateClient.getPassport());         // обновляем поля passport
            client.setPhone(updateClient.getPhone());               // обновляем поля phone
            client.setEmail(updateClient.getEmail());               // обновляем поле email
            return true;                                            // возвращаем true при успешном обновлении
        }
        return false;                                               // возвращаем false при неудачном
    }

}
