package apiTests.loading;

import controller.loading.UploadImageJpegControllerFluent;
import models.ApiHttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.File;

import static constants.CommonConstants.*;
import static constants.LoadingConstants.BEE_PATH_NAME;
import static constants.LoadingConstants.PETSTORE_UPLOADING_IMAGE_URL;

@Tags({@Tag(UPLOAD_TAG), @Tag(API_TAG)})
class UploadTests {
    @Test
    @Tags({@Tag(SMOKE_TAG), @Tag(POSITIVE_TAG)})
    @DisplayName("Upload an image to petstore")
    void apiUploadImageTest() {
        File file = new File(BEE_PATH_NAME);
        UploadImageJpegControllerFluent uploadImageController = new UploadImageJpegControllerFluent(file);

        ApiHttpResponse response = uploadImageController.uploadImage(PETSTORE_UPLOADING_IMAGE_URL);

        response.statusCodeIs(200);
        System.out.println(response);

//        File file = new File(BEE_PATH_NAME);
//
//        Response response =
//                given()
//                        .header("accept", "application/json")
//                        .contentType("multipart/form-data")
//                        .multiPart("file", file, "image/jpeg")
//                        .when()
//                        .post(PETSTORE_UPLOADING_IMAGE_URL)
//                        .then()
//                        .statusCode(200)
//                        .extract()
//                        .response();
//
//        System.out.println("Response: " + response.asString());
    }
}
