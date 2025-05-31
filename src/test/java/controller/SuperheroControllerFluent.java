package controller;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import models.ApiHttpResponse;
import models.SuperheroWithBuilder;

import static constants.SuperheroConstants.BASE_URL;
import static io.restassured.RestAssured.given;

public class SuperheroControllerFluent {
    RequestSpecification requestSpecification = given();

    public SuperheroControllerFluent() {
        RestAssured.defaultParser = Parser.JSON;
        this.requestSpecification.contentType(ContentType.JSON);
        this.requestSpecification.accept(ContentType.JSON);
        this.requestSpecification.baseUri(BASE_URL);
        this.requestSpecification.filter(new AllureRestAssured());
    }

    // CREATE - post
    @Step("Create superhero")
    public ApiHttpResponse createSuperhero(SuperheroWithBuilder superhero) {
        requestSpecification.body(superhero);
        return new ApiHttpResponse(given(requestSpecification)
                .when().post("superheroes")
                .then());
    }

    // GET - get
    @Step("Get superhero by id")
    public ApiHttpResponse getSuperheroesById(Integer id) {
        return new ApiHttpResponse(given(requestSpecification)
                .when().get("superheroes/" + id)
                .then());
    }

    @Step("Get superhero by id WITH RETRY")
    public ApiHttpResponse getSuperheroesByIdWithRetry(Integer id) throws InterruptedException {
        Response response = given(requestSpecification).when().get("superheroes/" + id);
        int statusCode = response.getStatusCode();

        if (statusCode != 200) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Waiting total " + (i + 1) + " second");
                Thread.sleep(1000);

                response = given(requestSpecification)
                        .when().get("superheroes/" + id);
                statusCode = response.getStatusCode();

                if (statusCode == 200) {
                    break;
                }
            }
        }

        return new ApiHttpResponse(response.then());
    }

    @Step("Get all superheroes")
    public ApiHttpResponse getAllSuperheroes() {
        return new ApiHttpResponse(given(requestSpecification)
                .when().get("superheroes")
                .then());
    }

    // UPDATE - put
    @Step("Update superhero by id")
    public ApiHttpResponse updateSuperhero(SuperheroWithBuilder superhero, Integer id) {
        requestSpecification.body(superhero);
        return new ApiHttpResponse(given(requestSpecification)
                .when().put("superheroes/" + id)
                .then());
    }

    @Step("Update superhero by id WITH RETRY")
    public ApiHttpResponse updateSuperheroWithRetry(SuperheroWithBuilder superhero, Integer id) throws InterruptedException {
        requestSpecification.body(superhero);
        Response response = given(this.requestSpecification)
                .when().put("superheroes/" + id);
        int statusCode = response.getStatusCode();

        if (statusCode != 200) {
            for (int i = 0; i < 10; i++) {
                System.out.println("Waiting total " + (i + 1) + " second");
                Thread.sleep(1000);

                response = given(this.requestSpecification)
                        .when().put("superheroes/" + id);
                statusCode = response.getStatusCode();

                if (statusCode == 200) {
                    break;
                }
            }
        }

        return new ApiHttpResponse(response.then());
    }

    // DELETE - delete
    @Step("Delete superhero by id")
    public ApiHttpResponse deleteSuperheroById(Integer id) {
        return new ApiHttpResponse(given(requestSpecification).log().all()
                .when().delete(String.format("superheroes/" + id))
                .then());
    }
}
