package api.tests.loading;

import api.controller.loading.DownloadPdfController;
import io.restassured.response.Response;
import api.models.ApiHttpResponse;
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
    DownloadPdfController downloadPdfController = new DownloadPdfController();

    @Test
    @Tags({@Tag(SMOKE_TAG), @Tag(POSITIVE_TAG)})
    @DisplayName("Download pdf file from the web site and check status code")
    void smokeApiDownloadHttpClientTest() {
        Response response = downloadPdfController.downloadPdf(ALFA_DOWNLOADING_PDF_URL);

        downloadPdfController.responseToApiHttpResponse(response)
                .statusCodeIs(200);

        savePdf(response, "downloaded.pdf");
    }

    @Test
    @Tags({@Tag(EXTENDED_TAG), @Tag(POSITIVE_TAG)})
    @DisplayName("Download pdf file from the web site and check downloaded file")
    void extendedApiDownloadHttpClientTest() {
        String fileName = "downloaded2.pdf";
        Response response = downloadPdfController.downloadPdf(ALFA_DOWNLOADING_PDF_URL);
        ApiHttpResponse apiHttpResponse = downloadPdfController.responseToApiHttpResponse(response)
                .statusCodeIs(200);

        long contentLength = savePdf(response, fileName);

        File downloadedFile = new File(fileName);
        apiHttpResponse
                .fileExists(downloadedFile)
                .fileSizeMatches(downloadedFile, (int) contentLength);
    }
}
