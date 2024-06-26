package steps;

import com.sun.net.httpserver.Request;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import utilities.DBUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.Matchers.equalTo;

public class ProductSteps {
    String id;


    RequestSpecification request;
    Response response;
    String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MjE5MjE4MTgsImlhdCI6MTcxOTMyOTgxOCwidXNlcm5hbWUiOiJxYXRlc3RlckBnbWFpbC5jb20ifQ.fv8GVvDXSsaJ-VZTQHjXem6QcqWI5gerLvuDAsRPShkF8Xnu7pkSJF4hUp7W70sB6-JeJQu_Bo8OuFxyBWhYew";
    JSONObject requestBody = new JSONObject();



    @Given("base url {string}")
    public void base_url(String baseUrl) {
        request = RestAssured.given().baseUri(baseUrl).contentType(ContentType.JSON);
    }

    @Given("I have access")
    public void i_have_access() {
        request = request.auth().oauth2(token);
    }

    @Given("I have the endpoint {string}")
    public void i_have_the_endpoint(String endpoint) {
        request = request.basePath(endpoint);
    }

    @Given("I have {string} with {string} in request body")
    public void i_have_with_in_request_body(String key, String value) {
        requestBody.put(key, value);
    }

    @When("I send POST request")
    public void i_send_post_request() {
        response = request.body(requestBody.toString()).post();
    }

    @When("I retrieve id for {string}")
    public void i_retrieve_id_for(String id) {
        this.id = response.jsonPath().getString(id);
    }

    @When("I send PUT request")
    public void i_send_put_request() {
      response = request.body(requestBody.toString()).put(id);
    }

    @Then("verify status code is {int}")
    public void verify_status_code_is(Integer statusCode) {
        System.out.println(response.prettyPrint());

        Assert.assertEquals(statusCode, response.statusCode());
    }

    @Then("verify I have {string} with {string} in response body")
    public void verify_i_have_with_in_response_body(String key, String value) {
        response.then()
                .body(key, equalTo(value));

    }

    @Then("verify I have {string} with null in response body")
    public void verify_i_have_with_null_in_response_body(String key) {
        response.then()
                .body(key, equalTo(null));
    }

    @Then("I delete the product")
    public void i_delete_the_product() {

        id = response.jsonPath().getString("product_id");

        response = RestAssured.given()
                .baseUri("https://backend.cashwise.us/api/myaccount")
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .when()
                .delete("/products/" + id);

        Assert.assertEquals(response.statusCode(), 200);

    }

    @Then("verify the product is not present in products list")
    public void verify_the_product_is_not_present_in_products_list() {
        response = RestAssured.given()
                .baseUri("https://backend.cashwise.us/api/myaccount/products/" + id)
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .when()
                .get();

        Assert.assertEquals(response.statusCode(), 500);

        String message = response.jsonPath().getString("details");
        Assert.assertTrue(message.contains("Product not found"));
    }

    @Given("I have {string} with product")
    public void i_have_with_product(String products) {

        Response response = RestAssured.given()
                .baseUri("https://backend.cashwise.us/api/myaccount")
                .contentType(ContentType.JSON)
                .auth()
                .oauth2(token)
                .get("/products/1718");
        System.out.println(response.prettyPrint());

        JSONObject product = new JSONObject();
        product.put("product_title", response.jsonPath().getString("product_title"));
        product.put("product_id", Integer.parseInt(response.jsonPath().getString("product_id")));
        product.put("count_of_product", 0);
        product.put("product_price", Double.parseDouble(response.jsonPath().getString("product_price")));
        product.put("service_type_id", Integer.parseInt(response.jsonPath().getString("service_type.service_type_id")));
        product.put("category_id", Integer.parseInt(response.jsonPath().getString("category.category_id")));
        product.put("product_description", response.jsonPath().getString("product_description"));

        JSONArray arrayOfProducts = new JSONArray();
        arrayOfProducts.put(product);

        requestBody.put(products, arrayOfProducts);

    }

    @Then("I delete {string} category in database")
    public void i_delete_category_in_database(String categoryTitle) throws SQLException {
        /*
        delete from products where category_id = ?; delete from categories where id = ?;
         */
        Connection connection = DBUtilities.getDBConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("delete from products where category_id = ?; delete from categories where id = ?");
        preparedStatement.setInt(1, Integer.parseInt(id));
        preparedStatement.setInt(2, Integer.parseInt(id));
        Assert.assertTrue(preparedStatement.executeUpdate() == 0);

    }

    @Then("verify {string} is deleted from database using GET request")
    public void verify_is_deleted_from_database_using_get_request(String categoryTitle) {

        response = request.get("/categories/" + id);

        System.out.println(response.prettyPrint());


    }
}
