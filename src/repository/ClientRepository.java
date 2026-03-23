package repository;

import model.Client;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientRepository {
    List<Client> clients = new ArrayList<>();

    /**
     * метод добавляет клиента  в List     *
     *
     * @param client
     * @return
     */
    public Client save(Client client) {
            clients.add(client);
            return client;
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
    public Optional<Client> findBuPhone(String phone) throws SQLException{

        String query = "SELECT * FROM clients WHILE phone=? ";
        Connection conn = DBconnect.getConnection();
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
     * поиск чувака по паспорту  возвращает Client
     */


        public Optional<Client> findByPassport(String passport){
            return clients.stream()
                .filter(client -> client.getPassport().equals(passport))
                .findFirst();
        }






    // удаляет чувака по id
    public boolean delete(int id) {
        return clients.removeIf(client -> client.getId() == id);
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
            client.setName(updateClient.getName());                 // обновляем поля Name
            client.setPassport(updateClient.getPassport());         // обновляем поля passport
            client.setPhone(updateClient.getPhone());               // обновляем поля phone
            client.setEmail(updateClient.getEmail());               // обновляем поле email
            return true;                                            // возвращаем true при успешном обновлении
        }
        return false;                                               // возвращаем false при неудачном
    }

}
