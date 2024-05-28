import com.sun.net.httpserver.Request;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static org.hamcrest.Matchers.equalTo;

public class IntroToPostRequest {
    public static void main(String[] args) {
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MTg1NDgzMTIsImlhdCI6MTcxNTk1NjMxMiwidXNlcm5hbWUiOiJxYXRlc3RlckBnbWFpbC5jb20ifQ.qEWtEi2XEK6GGxnMWZ3i98MiPrsfco9buqRe2sNVeHOAGVsbULtwfA39DogwEuPKP7jXIJtwaBBFd8kD6FwXiA";

        JSONObject requestBody = new JSONObject();
        requestBody.put("product_title", "Lagman");
        requestBody.put("product_price", 32.0);
        requestBody.put("service_type_id", 2);
        requestBody.put("category_id", 1023);
        requestBody.put("product_description", "Italian");
        requestBody.put("date_of_payment", "2024-05-20");
        requestBody.put("remind_before_day", 2);
        requestBody.put("do_remind_every_month", "REPEAT_EVERY_MONTH");

        RestAssured.given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .body(requestBody.toString())
                .when()
                .post("/myaccount/products")
                .then()
                .statusCode(201)
                .body("product_title", equalTo("Lagman"))
                .body("product_price", equalTo(32));

    }


}
