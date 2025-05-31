package apiTests.loading;

import controller.loading.DownloadPdfController;
import io.restassured.response.Response;
import models.ApiHttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.File;

import static constants.CommonConstants.*;
import static constants.LoadingConstants.ALFA_DOWNLOADING_PDF_URL;
import static utils.PdfUtils.savePdf;

@Tags({@Tag(DOWNLOAD_TAG), @Tag(API_TAG)})
class DownloadTests {
    @Test
    @Tags({@Tag(SMOKE_TAG), @Tag(POSITIVE_TAG)})
    @DisplayName("Download pdf file from the web site and check status code")
    void smokeApiDownloadHttpClientTest() {
        String fileName = "downloaded.pdf";
        DownloadPdfController downloadPdfController = new DownloadPdfController();

        Response response = downloadPdfController.downloadPdf(ALFA_DOWNLOADING_PDF_URL);
        ApiHttpResponse apiHttpResponse = downloadPdfController.responseToApiHttpResponse(response);

        apiHttpResponse.statusCodeIs(200);

        savePdf(response, fileName);

//        String fileName = "downloaded.pdf";
//
//        Response response =
//                given().
//                        when().
//                        get(ALFA_DOWNLOADING_PDF_URL).
//                        then().
//                        contentType("application/pdf").
//                        statusCode(200).
//                        extract().response();
//
//        savePdf(response, fileName);
//
//        File downloadedFile = new File(fileName);
//        assertThat(downloadedFile).exists();
    }

    @Test
    @Tags({@Tag(EXTENDED_TAG), @Tag(POSITIVE_TAG)})
    @DisplayName("Download pdf file from the web site and check downloaded file")
    void extendedApiDownloadHttpClientTest() {
        String fileName = "downloaded2.pdf";
        DownloadPdfController downloadPdfController = new DownloadPdfController();

        Response response = downloadPdfController.downloadPdf(ALFA_DOWNLOADING_PDF_URL);
        ApiHttpResponse apiHttpResponse = downloadPdfController.responseToApiHttpResponse(response);


        apiHttpResponse.statusCodeIs(200);

        long contentLength = savePdf(response, fileName);

        File downloadedFile = new File(fileName);
        apiHttpResponse
                .fileExists(downloadedFile)
                .fileSizeMatches(downloadedFile, (int)contentLength);
    }
}
