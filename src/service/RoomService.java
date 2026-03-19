package service;

import model.Room;
import repository.RoomRepository;

public class RoomService {
    private RoomRepository roomRepository;

    public RoomService() {
        this.roomRepository = new RoomRepository();
    }
    public Room addRoom(String roomNumber, String type, double pricePerNight){
        if (roomRepository.findByRoomNumber(roomNumber).isPresent()){
            System.out.println("Ошибка: Комната с таким номером уже существует!");
            return null;
        }
        Room room = new Room(roomNumber,type,pricePerNight);
        return roomRepository.save(room);
    }
    public  void printAllRooms(){
        System.out.println("показать все номера");
        System.out.println(roomRepository.getRooms());
    }
}
