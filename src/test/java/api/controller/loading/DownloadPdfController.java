package api.controller.loading;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import api.models.ApiHttpResponse;

import static io.restassured.RestAssured.given;

public class DownloadPdfController {
    RequestSpecification requestSpecification = given();

    public DownloadPdfController() {
        // requestSpecification settings for .given()
        requestSpecification.contentType("application/pdf");
        requestSpecification.filter(new AllureRestAssured());
    }

    @Step("Download a pdf file")
    public Response downloadPdf(String downloadingPdfUrl) {
        return given(requestSpecification)
                .when().get(downloadingPdfUrl)
                .then()
                .extract()
                .response();
    }

    public ApiHttpResponse responseToApiHttpResponse(Response response) {
        return new ApiHttpResponse(response.then());
    }
}
