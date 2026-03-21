import java.sql.*;

public class ConnectSql {

    static final String URL = "jdbc:mysql://127.0.0.1:3306/hotel";
    static final String USER = "root";
    static final String PASSWORD = "12345";

    public  void connectingSQL() {
        Connection connection;
        Statement statement;
        ResultSet resultSet;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Соединение с базой данных установлено!");
            statement = connection.createStatement();
//    statement.execute("INSERT INTO clients (name,lastname,email,phone,passport) VALUES ('kolya' , 'nicolaev' , 'fff@mail.ru' , '33333' , '77777')");
// для ввода новых данных в таблицу


// вывод всех name из таблицы
            resultSet = statement.executeQuery("SELECT * FROM clients");
//            while (resultSet.next()) {
//                 System.out.println(resultSet.next());  // проверка на наличие строки
//                System.out.println(resultSet.getString("name"));
//            }
//            System.out.println("==============================");
//
//            while (resultSet.next()) {
//                int id = resultSet.getInt("id");
//                String name = resultSet.getString("name");
//                String lastname = resultSet.getString("lastname");
//                String email = resultSet.getString("email");
//                String passport = resultSet.getString("passport");
//                System.out.printf("\nID: %d, name %s, lastname: %s, Email: %s, Passport: %s\n", id, name, lastname, email, passport);
//            }
//
//            Scanner scanner = new Scanner(System.in);
//            System.out.println("введите имя:");
//            String name = scanner.nextLine();
//            System.out.println("введите фамилию:");
//            String lastname = scanner.nextLine();
//            System.out.println("введите емайл:");
//            String email = scanner.nextLine();
//
//
//            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO clients (name,lastname,email) VALUES (?,?,?)");
//           preparedStatement.setString(1,name);
//           preparedStatement.setString(2,lastname);
//           preparedStatement.setString(3,email);
//
//
//           preparedStatement.executeUpdate();
//            System.out.println("Добавлен новый пользователь");
//






           statement.close();
            connection.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
