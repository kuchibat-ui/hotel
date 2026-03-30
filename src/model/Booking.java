package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking{


        private int id;
        private Client client;
        private Room room;
        private LocalDate checkInDate;
        private LocalDate CheckOutDate;
        private int guestsCount;
        private double totalPrice;
        private String status;

        public Booking(Client client, Room room, LocalDate checkInDate, LocalDate CheckOutDate, int guestsCount) {
            this.id = id;
            this.client = client;
            this.room = room;
            this.checkInDate = checkInDate;
            this.CheckOutDate = CheckOutDate;
            this.guestsCount = guestsCount;
            this.totalPrice = calculateTotalPrice();
            this.status = "Активно";
        }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return CheckOutDate;
    }

    public Room getRoom() {
        return room;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Client getClient() {
        return client;
    }

    private double calculateTotalPrice() {
            long nights = ChronoUnit.DAYS.between(checkInDate, CheckOutDate);
            return nights * room.getPricePerNight();
        }
    }

