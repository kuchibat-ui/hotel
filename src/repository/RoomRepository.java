package repository;

import model.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static repository.DBconnect.getConnection;

public class RoomRepository {
    private List<Room> rooms = new ArrayList<>();

    public Room save(Room room) {
        rooms.add(room);
        return room;
    }

    public void getRooms(Room room) throws SQLException {
        String query = "INSERT INTO room (roomnumber, type, pricePerNight) VALUES (?,?,?)";
        try {
            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, room.getRoomNumber());
            ps.setString(2, room.getType());
            ps.setDouble(3, room.getPricePerNight());

            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("комната " + room.getRoomNumber() + " " + room.getType() + " успешно добавлен в БД");

            } else {
                System.out.println(" комната не добавлена в БД");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * метод для поиска комнаты по id
     * применяется класс Optional потому что используется .findFirst()
     * позволяет избавится от проверки на null
     *
     * @param id комнаты ищет его в списке rooms
     * @return возвращает Room если id есть в списке rooms
     */
    public Optional<Room> findById(int id) {    // метод для поиска комнаты по id


        return rooms.stream()                   // запускаем stream по списку rooms
                .filter((room) -> room.getId() == id)   // в filter сравниваем id который пришел  id комнаты
                .findFirst();                   // находит первый подходящий id
    }


    /**
     * метод для поиска комнаты по roomNumber
     *
     * @param roomNumber
     * @return возвращает Room если переданный roomNumber соответствует roomNumber из списка rooms
     */
    public boolean findByRoomNumber(String roomNumber) throws SQLException {

        String query = "SELECT * FROM room WHERE roomnumber = ?";
        Connection conn = getConnection();
        Statement st = conn.createStatement();
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, roomNumber);                  //подстановка параметра phone в запрос
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return true;
//            int id = rs.getInt("id");
//            String roomnumber = rs.getString("roomnumber");
//            String type = rs.getString("type");
//            System.out.printf("\nID: %d, number %s,  type: %s\n", id, roomnumber, type);


        }
        return  false;

    }


    public List<Room> findAll() {
        return new ArrayList<>(rooms);
    }

    /**
     * метод принимает Room с новыми данными     *
     *
     * @param updateRoom Room которую нужно обновить
     * @return возвращает true при успешном обновлении и false при неудачном
     */
    public boolean update(Room updateRoom) {
        Optional<Room> existingRoom = findById(updateRoom.getId()); // вызов findById() найти Room с таким же id в rooms
        if (existingRoom.isPresent()) {                              //если Room с таким id существует, то
            Room room = existingRoom.get();                         // извлекаем этот Room из rooms
            room.setRoomNumber(updateRoom.getRoomNumber());         // обновляем поля roomNumber
            room.setType(updateRoom.getType());                     // обновляем поля type
            room.setPricePerNight(updateRoom.getPricePerNight());   // обновляем поля pricePerNight
            return true;                                            // возвращаем true при успешном обновлении
        }
        return false;                                               // возвращаем false при неудачном
    }





    /**
     * метод удаляет Room по его id
     *
     * @param id
     * @return true если Room есть и удален, и false когда такой Room нет и неудален
     */
    public void delete(int id) {
        String query = "DELETE FROM room WHERE id = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, id);                  //подстановка параметра passport в запро1с
            ps.executeUpdate();
            System.out.println("удалена строка " + id);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

//        return rooms.removeIf(room -> room.getId() == id); // для каждой Room в коллекции rooms
        // сравнивает ее id с переданным id
        // true если Room найдена и удалена, и false если такой Room нет
    }

    /**
     * метод удаляет комнату по номеру комнаты
     */
    public void deleteByNumber(int number){
        String query = "DELETE FROM room WHERE roomnumber = ?";
        try {
            Connection conn = DBconnect.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, number);                  //подстановка параметра passport в запро1с
            ps.executeUpdate();



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }





    public void clear() {
        rooms.clear();
    }


    public void showRooms() {
        String query = "SELECT * FROM room";
        try {
            Connection conn = getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String number = rs.getString("roomnumber");
                String type = rs.getString("type");
                System.out.printf("\n id: %d, number: %s, type: %s\n", id, number, type);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
