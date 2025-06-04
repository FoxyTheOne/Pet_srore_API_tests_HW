package api.controller.petstore.pet;

import api.models.ApiHttpResponse;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;

import java.io.File;

import static constants.PetStoreConstants.*;
import static io.restassured.RestAssured.given;

public class PetController implements IPetController {
    RequestSpecification requestSpecification = given();

    // Конструктор
    public PetController() {
        // requestSpecification settings for .given()
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.accept("application/json");
        requestSpecification.contentType("application/json");
        requestSpecification.filter(new AllureRestAssured());
    }

    @Override
    @Step("Upload an image")
    public ApiHttpResponse uploadImageById(File file, int petId, String metadata) {
        requestSpecification.contentType("multipart/form-data");
        requestSpecification.multiPart("additionalMetadata", metadata);
        requestSpecification.multiPart("file", file, "image/jpeg");

        return new ApiHttpResponse(given(requestSpecification)
                .when().post(PET_URL + "/" + petId + UPLOAD_IMAGE_URL)
                .then());
    }

//    @Override
//    public ApiHttpResponse addANewPetToTheStore(PetWithBuilder pet) {
//        // Реализация метода
//    }
//
//    @Override
//    public ApiHttpResponse updateAnExistingPet(PetWithBuilder pet) {
//        // Реализация метода
//    }
}
