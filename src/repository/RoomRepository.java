package repository;

import model.Room;
import java.sql.*;
import static repository.DBconnect.getConnection;

public class RoomRepository {

    Connection conn;
    {
        try {
            conn = getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void getRooms(Room room) throws SQLException {
        String query = "INSERT INTO room (roomnumber, type, pricePerNight) VALUES (?,?,?)";
        PreparedStatement ps = conn.prepareStatement(query); // для безопасности от SQL инъекций
        ps.setString(1, room.getRoomNumber());
        ps.setString(2, room.getType());
        ps.setDouble(3, room.getPricePerNight());
        int rows = ps.executeUpdate();               //изменяет данные в БД, возвращает количество измененных строк(int)
        if (rows > 0) {
            System.out.println("комната " + room.getRoomNumber() + " " + room.getType() + " успешно добавлен в БД");
        } else {
            System.out.println(" комната не добавлена в БД");
        }
        ps.close();
        conn.close();
    }


    /**
     * метод для поиска комнаты по id
     * применяется класс Optional потому что используется .findFirst()
     * позволяет избавится от проверки на null     *
     *
     * @return возвращает Room если id есть в списке rooms
     */

    public boolean existingRoomNumber(String roomNumber) throws SQLException {
        String query = "SELECT * FROM room WHERE roomnumber = ?";
        try (
                Connection conn = getConnection();
                PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, roomNumber);                     //подстановка параметра roomNumber в запрос
            try (ResultSet rs = ps.executeQuery()) {                      //executeQuery() -запрос на чтение данных из БД.
                                                                          // ResultSet -набор данных полученных из БД
                                                                          // Возвращает ResultQuery.
                return rs.next();                                         //если есть такая комната вернуть true            }
         // ps,conn закрыты автоматом так как обрабатывались исключением

        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }


/**
 * метод принимает Room с новыми данными     *
 *
 * @param updateRoom Room которую нужно обновить
 * @return возвращает true при успешном обновлении и false при неудачном
 */
//    public boolean update(Room updateRoom) {
//        Optional<Room> existingRoom = findById(updateRoom.getId()); // вызов findById() найти Room с таким же id в rooms
//        if (existingRoom.isPresent()) {                              //если Room с таким id существует, то
//            Room room = existingRoom.get();                         // извлекаем этот Room из rooms
//            room.setRoomNumber(updateRoom.getRoomNumber());         // обновляем поля roomNumber
//            room.setType(updateRoom.getType());                     // обновляем поля type
//            room.setPricePerNight(updateRoom.getPricePerNight());   // обновляем поля pricePerNight
//            return true;                                            // возвращаем true при успешном обновлении
//        }
//        return false;                                               // возвращаем false при неудачном
//    }


    /**
     * метод удаляет Room по его id
     *
     * @param id
     * @return true если Room есть и удален, и false когда такой Room нет и неудален
     */
    public void delete(int id) throws SQLException {
        String query = "DELETE FROM room WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);                  //подстановка параметра id в запрос query
        ps.executeUpdate();
        System.out.println("удалена строка " + id);
        ps.close();
        conn.close();
    }

//return rooms.removeIf(room -> room.getId() == id); // для каждой Room в коллекции rooms
// сравнивает ее id с переданным id
// true если Room найдена и удалена, и false если такой Room нет


    /**
     * метод удаляет комнату по номеру комнаты
     */
    public void deleteByNumber(int number) throws SQLException {
        String query = "DELETE FROM room WHERE roomnumber = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, number);                  //подстановка параметра number в запрос query
        ps.executeUpdate();
        ps.close();
        conn.close();
    }


    public void showRooms() throws SQLException {
        String query = "SELECT * FROM room";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            int id = rs.getInt("id");
            String number = rs.getString("roomnumber");
            String type = rs.getString("type");
            System.out.printf("\n id: %d, number: %s, type: %s\n", id, number, type);
        }
        rs.close();
        conn.close();

    }
}