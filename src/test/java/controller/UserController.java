package controller;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.hw14.User;

import static constants.PetStoreConstants.BASE_URL;
import static io.restassured.RestAssured.given;

public class UserController {
    RequestSpecification requestSpecification = given();

    public UserController() {
        // Повторяющиеся шаги:
        // given()
        // .baseUri("https://petstore.swagger.io")
        requestSpecification.baseUri(BASE_URL);
        // .header("accept", "application/json")
        requestSpecification.accept("application/json");
        // .header("Content-Type", "application/json")
        requestSpecification.contentType("application/json");
        requestSpecification.filter(new AllureRestAssured());
    }

    @Step("Create user")
    public Response createUser(User user) {
        // .when()
        // .body(body)
        requestSpecification.body(user);
        // .post("/v2/user")
        return given(requestSpecification).when().post("/v2/user");
    }

    @Step("Get user by username")
    public User getUserByUsername(String username) {
        return given(requestSpecification).when().get("/v2/user/" + username).as(User.class);
    }

    @Step("Update user")
    public Response updateUser(User user, String username) {
        requestSpecification.body(user);
        return given(requestSpecification).log().all().when().put("/v2/user/" + username);
    }

    @Step("Delete user")
    public Response deleteUser(String username) {
        return given(requestSpecification).log().all().when().delete("/v2/user/" + username);
    }
}
