package controller;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import models.UserApiHttpResponse;
import models.UserWithBuilder;

import static constants.CommonConstants.BASE_URL;
import static io.restassured.RestAssured.given;

public class UserControllerFluent {
    RequestSpecification requestSpecification = given();

    // Конструктор
    public UserControllerFluent() {
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
    public UserApiHttpResponse createUser(UserWithBuilder user) {
        // .when()
        // .body(body)
        requestSpecification.body(user);
        // .post("/v2/user")
        return new UserApiHttpResponse(given(requestSpecification).when().post("/v2/user").then());
    }

    @Step("Get user by username")
    public UserApiHttpResponse getUserByUsername(String username) {
        return new UserApiHttpResponse(given(requestSpecification).when().get("/v2/user/" + username).then());
    }

    @Step("Update user")
    public UserApiHttpResponse updateUser(UserWithBuilder user, String username) {
        requestSpecification.body(user);
        return new UserApiHttpResponse(given(requestSpecification).log().all().when().put("/v2/user/" + username).then());
    }

    @Step("Delete user by name")
    public UserApiHttpResponse deleteUserByName(String username) {
        return new UserApiHttpResponse(given(requestSpecification).log().all().when().delete("/v2/user/" + username).then());
    }
}
