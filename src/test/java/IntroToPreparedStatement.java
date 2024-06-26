import java.sql.*;

public class IntroToPreparedStatement {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:oracle:thin:@35.158.255.250:1521:CODEWISE";
        String username = "SYSTEM";
        String password = "Codewise_123";

        Connection connection = DriverManager.getConnection(url, username, password);

        PreparedStatement preparedStatement = connection.prepareStatement("select * from EMPLOYEES where FIRST_NAME = ?");
        preparedStatement.setString(1, "Olya");

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            System.out.println(resultSet.getString("first_name"));
            System.out.println(resultSet.getString("last_name"));
        }

    }
}
