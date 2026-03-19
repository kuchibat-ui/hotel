package model;

import java.util.Objects;

public class Room {
    private static int nextId = 1;
    private int id;
    private String roomNumber;
    private String type;
    private String status;
    private  double pricePerNight;

    public Room(String roomNumber, String type, double pricePerNight) {
        this.id = nextId++;
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
    }

    public int getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public String getStatus() {
        return status;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;            // сравниваем объекты, если ссылки на один объект то true
        if (obj==null || getClass() != obj.getClass()) return false;  //если ссылка на несущест объект или классы разные то false
        Room room = (Room) obj;    // вернуть true если
        return id == room.id;      // id передаваемого и id этого равны, иначе false
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
