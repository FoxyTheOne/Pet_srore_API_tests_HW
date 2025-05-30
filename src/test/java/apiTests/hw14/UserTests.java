package apiTests.hw14;

import controller.UserController;
import io.restassured.response.Response;
import models.hw14.User;
import models.hw14.UserApiResponse;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import static constants.PetStoreConstants.*;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserTests {
    @Test
    void simpleCreateUserTest() {
        // given().header("accept", "application/json").header("Content-Type", "application/json").baseUri("https://petstore.swagger.io") + .filter(new AllureRestAssured())
        UserController userController = new UserController();

        // .when().body(body).post("/v2/user")
        Response response = userController.createUser(DEFAULT_STRING_USER);

        int actualCode = response.getStatusCode();

        assertEquals(200, actualCode);
    }

    @Test
    void createUserWithSerializationTest() {
        // given()
        UserController userController = new UserController();

        // .when().body(body).post("/v2/user")
        Response response = userController.createUser(DEFAULT_STRING_USER);

        // Парсинг
        UserApiResponse user = response.as(UserApiResponse.class);
        // Проверки
        assertEquals(200, user.getCode());
        assertEquals("unknown", user.getType());
        assertTrue(Long.parseLong(user.getMessage()) > 9223372036854764397L);
    }

    @Test
    void createUserTest() {
        String body = """
                {
                    "id": 0,
                    "username": "string",
                    "firstName": "string",
                    "lastName": "string",
                    "email": "string",
                    "password": "string",
                    "phone": "string",
                    "userStatus": 0
                }
                """;
        given().
                baseUri(BASE_URL).
                //header("accept", "application/json").
                //header("Content-Type", "application/json").
                        accept("application/json").
                contentType("application/json").
                body(body).
                log().all().
                when().
                post("/v2/user").
                then().
                statusCode(200).
                log().all().
                body("code", Matchers.equalTo(200),
                        "type", Matchers.equalTo("unknown"),
                        "message", Matchers.greaterThan("9223372036854768470"));
    }

    @Test
    void getUserByUsernameTest() {
        // given()
        UserController userController = new UserController();

        // .when().get("/v2/user/" + username)
        User user = userController.getUserByUsername("string");

//        assertEquals(200, response.getStatusCode());
//        User user = response.as(User.class);
        assertTrue(user.getId() > 9223372036854764397L);
        assertEquals(expectedUser, user);
    }

    @Test
    void updateUserTest() {
        // given()
        UserController userController = new UserController();

        // .when()
        Response response = userController.updateUser(STRING2_USER, STRING2_USER.getUsername());

        // response.then().statusCode(200).
        assertEquals(200, response.getStatusCode());
        UserApiResponse user = response.as(UserApiResponse.class);
        assertEquals(200, user.getCode());
        assertEquals("unknown", user.getType());
        assertEquals("1", user.getMessage());
    }

    @Test
    void deleteUserTest() {
        // given()
        UserController userController = new UserController();

        // .when()
        Response response = userController.deleteUser(STRING2_USER.getUsername());

        UserApiResponse user = response.as(UserApiResponse.class);
        assertEquals(200, user.getCode());
        assertEquals("unknown", user.getType());
        assertEquals(STRING2_USER.getUsername(), user.getMessage());
    }
}
