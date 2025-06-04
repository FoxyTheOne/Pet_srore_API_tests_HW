package api.controller.petstore.user;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import api.models.ApiHttpResponse;
import api.models.petstore.user.UserWithBuilder;

import static constants.PetStoreConstants.BASE_URL;
import static constants.PetStoreConstants.USER_URL;
import static io.restassured.RestAssured.given;

public class UserControllerFluent implements IUserController {
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

    // CREATE - post
    @Step("Create user")
    public ApiHttpResponse createUser(UserWithBuilder user) {
        // .when()
        // .body(body)
        requestSpecification.body(user);
        // .post("/v2/user")
        return new ApiHttpResponse(given(requestSpecification).when().post(USER_URL).then());
    }

    @Step("Create user with retry")
    public ApiHttpResponse createUserWithRetry(UserWithBuilder user) throws InterruptedException {
        requestSpecification.body(user);

        Response response = given(requestSpecification).when().post(USER_URL);

        int statusCode = response.getStatusCode();
        if (statusCode != 200) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Waiting total " + (i + 1) + " second");
                Thread.sleep(1000);
                response = given(requestSpecification).when().post(USER_URL);
                statusCode = response.getStatusCode();
                if (statusCode == 200) {
                    break;
                }
            }
        }

        return new ApiHttpResponse(response.then());
    }

    // GET - get
    @Step("Get user by username")
    public ApiHttpResponse getUserByUsername(String username) {
        return new ApiHttpResponse(given(requestSpecification).when().get(USER_URL + "/" + username).then());
    }

    // UPDATE - put
    @Step("Update user by username")
    public ApiHttpResponse updateUser(UserWithBuilder user, String username) {
        requestSpecification.body(user);
        return new ApiHttpResponse(given(requestSpecification).log().all().when().put(USER_URL + "/" + username).then());
    }

    // DELETE - delete
    @Step("Delete user by name")
    public ApiHttpResponse deleteUserByName(String username) {
        return new ApiHttpResponse(given(requestSpecification).log().all().when().delete(USER_URL + "/" + username).then());
    }
}
