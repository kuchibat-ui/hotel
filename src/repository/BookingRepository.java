package repository;

import model.Booking;
import model.Client;
import model.Room;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static repository.DBconnect.getConnection;

public class BookingRepository {
ClientRepository clientRepository;
    Connection conn;

    {
        try {
            conn = getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean isRoomOccupied(int id, LocalDate checkInDate, LocalDate checkOutDate) {
        String query = "SELECT COUNT(*) FROM bookings " +                    //запрос в базу данных
                "WHERE room_id = ? " +
                "AND status != 'CANCELLED' " +
                "AND check_in_date <= ? AND check_out_date > ?";

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);
            ps.setDate(2, Date.valueOf(checkOutDate));
            ps.setDate(3, Date.valueOf(checkInDate));
            ResultSet rs = ps.executeQuery();                       // executeQuery() -запрос на чтение данных из БД.
            if (rs.next()) {
                return rs.getInt(1) > 0;       //rs.getInt(1) вернет Int количество  совпадедний >0 (false/true)
            }
            return false;                   // вставка запроса и безопасность от SQL инъекций
        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении бронирования: " + e.getMessage());
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


    public void save(Booking booking)  {
        String query = "INSERT INTO bookings (room_id,client_id,check_in_date,check_out_date) VALUES (?,?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query); // для безопасности от SQL инъекций
            ps.setInt(1, booking.getRoom().getId());
            ps.setInt(2, booking.getClient().getId());
            ps.setDate(3, Date.valueOf(booking.getCheckInDate()));
            ps.setDate(4, Date.valueOf(booking.getCheckOutDate()));
            int rows = ps.executeUpdate();               //изменяет данные в БД, возвращает количество измененных строк(int)
            if (rows > 0) {
                System.out.println("успешное бронирование");
            } else {
                System.out.println("Ошибка");
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при сохранении бронирования: " + e.getMessage());
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

    public void findById(int id) {
        String query = "SELECT * FROM bookings WHERE id=? ";

        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(query);
            ps.setInt(1, id);                  //подстановка параметра id в запро1с
            ResultSet rs = ps.executeQuery();               //executeQuery() -запрос на чтение данных из БД.
                                                           // ResultSet -набор данных полученных из БД
                                                           // Возвращает ResultQuery.
            if (rs.next()) {
                int roomId = rs.getInt("room_id");
                int clientId = rs.getInt("client_id");
                java.util.Date dateIn = rs.getDate("check_in_date");
                java.util.Date dateOut = rs.getDate("check_out_date");


                System.out.printf("id комнаты: %d, id гостя: %d, дата въезда: %s, дата выезда: %s", roomId,clientId,dateIn,dateOut);
//                System.out.println(clientRepository.findById(id));

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

    }

    // взамиодействия клиента и комнаты
        // условия заселения
        // комната свободна или на ремонте, клиент еще не заселен

    }
    //отмена бронирования

