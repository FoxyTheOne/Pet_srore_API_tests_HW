package models;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class UserApiHttpResponse {
    private final ValidatableResponse response;

    public UserApiHttpResponse(ValidatableResponse response) {
        this.response = response;
    }

    @Step("Check status code")
    public UserApiHttpResponse statusCodeIs(int status) {
        this.response.statusCode(status);
        return this;
    }

    @Step("Check json value by path '{fieldPath}' and expected value '{expectedFieldValue}'")
    public UserApiHttpResponse jsonStringValueIs(String fieldPath, String expectedFieldValue) {
        String actualValue = this.response.extract().jsonPath().getString(fieldPath);
        String messageIfFailed = String.format("Actual value '%s' is not equals to expected '%s' for the path '%s' and response: \n%s",
                actualValue,
                expectedFieldValue,
                fieldPath,
                this.response.extract().response().andReturn().asPrettyString());

        assertThat(actualValue).as(messageIfFailed).isEqualTo(expectedFieldValue);
        return this;
    }

    @Step("Check json value by path '{fieldPath}' and expected value '{expectedFieldValue}'")
    public UserApiHttpResponse jsonLongValueIs(String fieldPath, long expectedFieldValue) {
        long actualValue = this.response.extract().jsonPath().getLong(fieldPath);
        String messageIfFailed = String.format("Actual value '%s' is not equals to expected '%s' for the path '%s' and response: \n%s",
                actualValue,
                expectedFieldValue,
                fieldPath,
                this.response.extract().response().andReturn().asPrettyString());

        assertThat(actualValue).as(messageIfFailed).isEqualTo(expectedFieldValue);
        return this;
    }

    @Step("Check json value is not null")
    public UserApiHttpResponse jsonValueIsNotNull(String path) {
        String actualValue = this.response.extract().jsonPath().getString(path);
        assertThat(actualValue).isNotNull();
        return this;
    }

    @Step("Check json value is null")
    public UserApiHttpResponse jsonValueIsNull(String path) {
        String actualValue = this.response.extract().jsonPath().getString(path);
        assertThat(actualValue).isNull();
        return this;
    }

    @Step("Get json value by path: {fieldPath} and check that the value is not null")
    public String getJsonValueAndCheckIsNotNull(String fieldPath) {
        String value = this.response.extract().jsonPath().getString(fieldPath);
        assertThat(value).isNotNull();
        return value;
    }

    @Override
    @Step("Return info about response")
    public String toString() {
        return String.format("Status code: %s and response: \n%s", response.extract().response().statusCode(), response.extract().response().asPrettyString());
    }
}
