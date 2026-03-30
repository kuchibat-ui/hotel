package repository;

//ТОЛЬКО ДЛЯ БАЗЫ ДАННЫХ
//НЕ ДОЛЖЕН СОДЕРЖАТЬ БИЗНЕС ЛОГИКУ
// НЕ ДОЛЖЕН ВЫЗЫВАТЬ CLIENTSERVICE
// ПОДКЛЮЧЕНИЕ К БАЗЕ ДАННЫХ

import model.Client;

import java.sql.*;
import java.util.Optional;

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
    public void saveRepo(Client client) {
        String query = "INSERT INTO clients (lastname,name,email,passport,phone) VALUES (?,?,?,?,?)";

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);

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

        } catch (SQLException e) {
            System.err.println("Ошибка: такого клиента нет " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Ошибка при закрытии: " + e.getMessage());
                }
            }
        }
    }


    /**
     * поиск по EMAIL
     */
    public boolean existingEmail(String email) throws SQLException {
        String query = "SELECT * FROM clients WHERE email=? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, email);                  //подстановка параметра email в запро1с
            ResultSet rs = ps.executeQuery();               //executeQuery() -запрос на чтение данных из БД.
            // ResultSet -набор данных полученных из БД
            // Возвращает ResultQuery.
            return rs.next();

        } catch (SQLException e) {
            System.err.println("Ошибка: такого номера в гостинице нет " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Ошибка при закрытии: " + e.getMessage());
                }
            }
        }
        return false;


    }


    /**
     * поиск клиента по id
     *
     * @param id
     * @throws SQLException
     */
    public boolean searchById(int id) {
        String query = "SELECT * FROM clients WHERE id=? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);                  //подстановка параметра id в запро1с
            ResultSet rs = ps.executeQuery();               //executeQuery() -запрос на чтение данных из БД.
            // ResultSet -набор данных полученных из БД
            // Возвращает ResultQuery.

            if (rs.next()) {
//                String lastname = rs.getString("lastname");
//                String name = rs.getString("name");
//                String email = rs.getString("email");
//                String pass = rs.getString("passport");
//                int phone = rs.getInt("phone");
//                String telephone = String.valueOf(phone);
//                Client client = new Client(lastname,name,email,pass,telephone);
                return true;
            }

        } catch (SQLException e) {
            System.err.println("Ошибка:" + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Ошибка при закрытии: " + e.getMessage());
                }
            }
        }
        return false;
    }


    // поиск чувака по телефону
    public void findByPhone(String phone) {
        String query = "SELECT * FROM clients WHERE phone=? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
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

        } catch (SQLException e) {
            System.err.println("Ошибка: гостя с таким номером телефона в базе нет " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Ошибка при закрытии: " + e.getMessage());
                }
            }

        }
    }

    /**
     * поиск чувака по паспорту
     */
    public void findByPass(String passport) {
        String query = "SELECT * FROM clients WHERE passport=? ";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
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

        } catch (SQLException e) {
            System.err.println("Ошибка: таким номером паспорта в базе нет " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Ошибка при закрытии: " + e.getMessage());
                }
            }
        }

    }

    /**
     * УДАЛЯЕТ КЛИЕНТА ПО ID
     *
     * @param id
     */
    public void delete(int id) {
        String query = "DELETE FROM clients WHERE id = ?";
        PreparedStatement ps = null;
        try {
            ps =  conn.prepareStatement(query);
            ps.setInt(1, id);                  //подстановка параметра в запрос
            ps.executeUpdate();
            System.out.println("удалена строка " + id);
            ps.close();

        } catch (SQLException e) {
            System.err.println("Ошибка: гостя с таким id нет в базе " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Ошибка при закрытии: " + e.getMessage());
                }
            }
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
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String lastname = rs.getString("lastname");
            System.out.printf("\n id: %d, lastname: %s, name: %s\n", id, name, lastname);
        }
        rs.close();

    }

    /**
     * удаление клиента по фамилии
     *
     * @param lastname
     */
    public void deleteByLastname(String lastname)  {
       String query = "DELETE FROM clients WHERE lastname = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, lastname);  //подстановка параметра lastname в запрос
            ps.executeUpdate();
            System.out.println("удален клиент из базы данных " + lastname);

        } catch (SQLException e) {
            System.err.println("Ошибка: гостя с такой фамилией нет в базе  " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Ошибка при закрытии: " + e.getMessage());
                }
            }
        }
    }


    public Client findById(int id) {
        String query = "SELECT * FROM clients WHERE id=? ";

        PreparedStatement ps = null;
        try {
          ps = conn.prepareStatement(query);
            ps.setInt(1, id);                  //подстановка параметра id в запро1с
            ResultSet rs = ps.executeQuery();               //executeQuery() -запрос на чтение данных из БД.
            // ResultSet -набор данных полученных из БД
            // Возвращает ResultQuery.

            if (rs.next()) {
                String lastname = rs.getString("lastname");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String pass = rs.getString("passport");
                int phone = rs.getInt("phone");
                String telephone = String.valueOf(phone);
                Client client = new Client(lastname, name, email, pass, telephone);
                client.setId(id);
                return client;
            }

        } catch (SQLException e) {
            System.err.println("Ошибка: такого номера в гостинице нет " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException e) {
                    System.err.println("Ошибка при закрытии: " + e.getMessage());
                }
            }

        }
        return null;
    }
}

// поиск чувака по id
//    public Optional<Client> searchById(int id) {
//        return clients.stream()
//                .filter(client -> client.getId() == id)
//                .findFirst();
//    }}

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


