import org.testng.annotations.Test;
import utilities.DBUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PrintSQLResults {


    @Test
    public void printResults() throws SQLException {
        // print all products: id, title, price which are between 2000 and 5000
        Connection connection = DBUtilities.getDBConnection();
        String sqlQuery = "select distinct id, title, price from products where title is not null";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            String[] title = resultSet.getString("title").trim().split("\\s+");
            if (title.length == 3){
                System.out.println(resultSet.getString("title") + "\t" + resultSet.getString("id") + "\t" + resultSet.getString("price"));
            }
        }
        // EXTRA: print only those titles that contain 3 words
    }

    @Test
    public void printCategories() throws SQLException {
        Connection connection = DBUtilities.getDBConnection();
        String sqlQuery = "select id, title, description, created from categories\n" +
                "where title <> '' and description <> ''\n" +
                "order by title asc";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        String oldTitle;
        while (resultSet.next()){
            String  title = resultSet.getString("title");

            if(title.equals("QA Engineer")){

                System.out.println("title: " + title.toUpperCase());
            } else {
                System.out.println("title: " + title.toLowerCase());
            }
        }

        /*
        -- get id, title, description of categories, created
        -- get only the date
        -- sort by title in asc
        -- get only unique titles
        -- if title is = QA Engineer, print in all uppercase
        -- all other values print in lowercase
         */
    }
}
