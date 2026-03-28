package repository;

//ТОЛЬКО ДЛЯ БАЗЫ ДАННЫХ
//НЕ ДОЛЖЕН СОДЕРЖАТЬ БИЗНЕС ЛОГИКУ
// НЕ ДОЛЖЕН ВЫЗЫВАТЬ CLIENTSERVICE
// ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ

import model.Client;

import java.sql.*;

import static repository.DBconnect.getConnection;

public class ClientRepository {

    Connection conn;

    {
        try {
            conn = getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * метод добавляет клиента  в БД        *
     *
     * @param client
     * @return
     */
    public void saveRepo(Client client) throws SQLException {
        String query = "INSERT INTO clients (lastname,name,email,passport,phone) VALUES (?,?,?,?,?)";

        PreparedStatement ps = conn.prepareStatement(query);

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
        ps.close();
        conn.close();
    }


    /**
     * поиск клиента по id
     *
     * @param id
     * @throws SQLException
     */
    public void findById(int id) {
        String query = "SELECT * FROM clients WHERE id=? ";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);                  //подстановка параметра id в запро1с
            ResultSet rs = ps.executeQuery();               //executeQuery() -запрос на чтение данных из БД.
            // ResultSet -набор данных полученных из БД
            // Возвращает ResultQuery.

            while (rs.next()) {
                String lastname = rs.getString("lastname");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String pass = rs.getString("passport");
                System.out.printf("\nlastname: %s, name %s,  Email: %s, Passport: %s\n", lastname, name, email, pass);
            }
            ps.close();
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println(" клиента с таким id не существует");
        }

    }



    // поиск чувака по телефону
    public void findByPhone(String phone) throws SQLException {
        String query = "SELECT * FROM clients WHERE phone=? ";
        try {
            Statement st = conn.createStatement();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, phone);                  //подстановка параметра phone в запрос
            ResultSet rs = ps.executeQuery();                    //executeQuery() -запрос на чтение данных из БД.

            while (rs.next()) {
                int id = rs.getInt("id");
                String lastname = rs.getString("lastname");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String pass = rs.getString("passport");
                System.out.printf("\nID: %d,lastname: %s, name %s,  Email: %s, Passport: %s\n", id, lastname, name, email, pass);
            }
            ps.close();
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("клиента с таким номера телефона нет в базе данных");
        }
    }

    /**
     * поиск чувака по паспорту
     */
    public void findByPass(String passport) {
        String query = "SELECT * FROM clients WHERE passport=? ";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, passport);                  //подстановка параметра passport в запрос
            ResultSet rs = ps.executeQuery();                        //executeQuery() -запрос на чтение данных из БД.

            while (rs.next()) {
                int id = rs.getInt("id");
                String lastname = rs.getString("lastname");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String pass = rs.getString("passport");
                System.out.printf("\nID: %d,lastname: %s, name %s,  Email: %s, Passport: %s\n", id, lastname, name, email, pass);
            }
            ps.close();
            rs.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("клиента с таким номером паспорта нет в базе данных");
        }
    }

    /**
     * УДАЛЯЕТ КЛИЕНТА ПО ID
     *
     * @param id
     */
    public void delete(int id) {
        String query = "DELETE FROM clients WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);                  //подстановка параметра в запрос
            ps.executeUpdate();
            System.out.println("удалена строка " + id);
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("клиента с таким ID нет");
        }
    }

    /**
     * получить все данные клиентов из базы данных        *
     *
     * @return List
     */
    public void findAll() throws SQLException {
        String query = "SELECT * FROM clients";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            String name = rs.getString("name");
            String lastname = rs.getString("lastname");
            System.out.printf("\n lastname: %s, name: %s\n", name, lastname);
        }
        rs.close();
        conn.close();
    }

    /**
     * удаление клиента по фамилии
     *
     * @param lastname
     */
    public void deleteByLastname(String lastname) throws SQLException {
        try {
            String query = "DELETE FROM clients WHERE lastname = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, lastname);  //подстановка параметра lastname в запрос
            ps.executeUpdate();
            System.out.println("удален клиент из базы данных " + lastname);
            ps.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("клиента с такой фамилией нет в базе данных");
        }
    }
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


