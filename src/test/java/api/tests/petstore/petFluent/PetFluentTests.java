package api.tests.petstore.petFluent;

import api.models.ApiHttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.File;

import static constants.CommonConstants.*;
import static constants.PetStoreConstants.BEE_PATH_NAME;

@Tag(API_TAG)
class PetFluentTests extends BasePetFluentTests {
    @Test
    @Tags({@Tag(SMOKE_TAG), @Tag(UPLOAD_TAG), @Tag(POSITIVE_TAG)})
    @DisplayName("Upload an image by id to petstore")
    void apiUploadImageTest() {
        File file = new File(BEE_PATH_NAME);

        ApiHttpResponse response = petController.uploadImageById(file, 1, "555")
                .statusCodeIs(200);
        System.out.println(response);
    }
}
