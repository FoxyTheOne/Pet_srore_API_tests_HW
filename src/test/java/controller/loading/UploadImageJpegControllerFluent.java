package controller.loading;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.specification.RequestSpecification;
import models.ApiHttpResponse;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UploadImageJpegControllerFluent {
    RequestSpecification requestSpecification = given();

    public UploadImageJpegControllerFluent(File file) {
        // requestSpecification settings for .given()
        requestSpecification.accept("application/json");
        requestSpecification.contentType("multipart/form-data");
        requestSpecification.multiPart("file", file, "image/jpeg");
        requestSpecification.filter(new AllureRestAssured());
    }

    @Step("Upload an image")
    public ApiHttpResponse uploadImage(String uploadingImageUrl) {
        return new ApiHttpResponse(given(requestSpecification)
                .when().post(uploadingImageUrl)
                .then());
    }
}
