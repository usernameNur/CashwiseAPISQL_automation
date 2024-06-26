package steps;

import io.cucumber.java.en.Then;
import org.testng.Assert;
import utilities.Config;
import utilities.DBUtilities;

import java.sql.*;

public class DBSteps {

    @Then("verify {string} with {string} is in database")
    public void verify_with_is_in_database(String key, String value) throws SQLException {

        Connection connection = DBUtilities.getDBConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("select * from products where title = ?");
        preparedStatement.setString(1, value);

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            Assert.assertTrue(resultSet.getString("title").equals(value));
        }



    }

    @Then("verify {string} with {string} is not in database")
    public void verify_with_is_not_in_database(String key, String value) {

    }

}
