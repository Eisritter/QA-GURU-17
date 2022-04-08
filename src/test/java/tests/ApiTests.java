package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class ApiTests {

    @Test
    void getListUsersTest() {
        given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("total", is(12));
    }

    @Test
    void createUserTests() {
        String data = "{\"name\": \"morpheus\", \"job\": \"leader\"}";

        given()
                .contentType(JSON)
                .body(data)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", is("morpheus"));
    }

    @Test
    void UpdateUserTests() {
        String dataUpdate = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";

        given()
                .contentType(JSON)
                .body(dataUpdate)
                .when()
                .put("https://reqres.in/api/users/2")
                .then()
                .statusCode(200)
                .body("job", is("zion resident"));
    }

    @Test
    void SuccessfulRegisterTest(){
        String dataRegister = "{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}";

        given()
                .contentType(JSON)
                .body(dataRegister)
                .when()
                .post("https://reqres.in/api/register")
                .then()
                .statusCode(200)
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    void DeleteUserTest(){
        given()
                .when()
                .delete("https://reqres.in/api/users/2")
                .then()
                .statusCode(204);
    }
}
