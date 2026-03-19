package repository;

import model.Room;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomRepository {
    private List<Room> rooms = new ArrayList<>();

    public Room save(Room room) {
        rooms.add(room);
        return room;
    }

    public List<Room> getRooms() {
        return rooms;
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
    public Optional<Room> findByRoomNumber(String roomNumber) {
        return rooms.stream()
                .filter((room) -> room.getRoomNumber().equals(roomNumber))
                .findFirst();
    }

    public List<Room> findAll() {
        return new ArrayList<>(rooms);
    }

    /**
     * метод принимает Room с новыми данными
     *
     * @param updateRoom  Room которую нужно обновить
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
     * @param id
     * @return true если Room есть и удален, и false когда такой Room нет и неудален
     */
    public boolean delete(int id){
        return rooms.removeIf(room -> room.getId() == id); // для каждой Room в коллекции rooms
                                                           // сравнивает ее id с переданным id
        // true если Room найдена и удалена, и false если такой Room нет
    }

    public void clear(){
        rooms.clear();
    }


}
