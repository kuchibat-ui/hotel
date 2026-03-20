package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Booking{

        private static int nextId = 1;
        private int id;
        private Client client;
        private Room room;
        private LocalDate checkInDate;
        private LocalDate CheckOutDate;
        private int guestsCount;
        private double totalPrice;
        private String status;

        public Booking(Client client, Room room, LocalDate checkInDate, LocalDate CheckOutDate, int guestsCount) {
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

    private double calculateTotalPrice() {
            long nights = ChronoUnit.DAYS.between(checkInDate, CheckOutDate);
            return nights * room.getPricePerNight();
        }
    }

