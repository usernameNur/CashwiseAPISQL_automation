
import io.restassured.RestAssured;

public class IntroToAPI {

    public static void main(String[] args) {

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE3MTg1NDgzMTIsImlhdCI6MTcxNTk1NjMxMiwidXNlcm5hbWUiOiJxYXRlc3RlckBnbWFpbC5jb20ifQ.qEWtEi2XEK6GGxnMWZ3i98MiPrsfco9buqRe2sNVeHOAGVsbULtwfA39DogwEuPKP7jXIJtwaBBFd8kD6FwXiA";

        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/sellers/all")
                .then()
                .statusCode(200);

        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/tags/all")
                .then()
                .statusCode(200);

        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .and()
                .queryParam("page", "1")
                .queryParam("size", 4)
                .when()
                .get("/myaccount/reminder/requests")
                .then()
                .statusCode(200);

        RestAssured.given()
                .auth()
                .oauth2(token)
                .baseUri("https://backend.cashwise.us/api")
                .when()
                .get("/myaccount/reminder/notifications")
                .then()
                .statusCode(200);






    }

}
