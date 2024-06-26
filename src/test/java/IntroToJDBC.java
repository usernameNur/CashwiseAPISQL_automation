import java.sql.*;

public class IntroToJDBC {
    public static void main(String[] args) throws SQLException {

        String url = "jdbc:oracle:thin:@35.158.255.250:1521:CODEWISE";
        String username = "SYSTEM";
        String password = "Codewise_123";

        Connection connection = DriverManager.getConnection(url, username, password);

        Statement statement = connection.createStatement();

        String sqlQuery = "select * from employees";
        ResultSet resultSet = statement.executeQuery(sqlQuery);

        while(resultSet.next()){
            System.out.println(resultSet.getString("first_name"));
        }

    }
}
