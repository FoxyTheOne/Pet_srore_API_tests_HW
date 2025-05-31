package models;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ApiHttpResponse {
    private final ValidatableResponse response;

    public ApiHttpResponse(ValidatableResponse response) {
        this.response = response;
    }

    @Step("Check status code")
    public ApiHttpResponse statusCodeIs(int status) {
        this.response.statusCode(status);
        return this;
    }

    @Step("Check json value by path '{fieldPath}' and expected value '{expectedFieldValue}'")
    public ApiHttpResponse jsonValueIs(String fieldPath, Object expectedFieldValue) {
        Object actualValue;

        if (expectedFieldValue != null) {

            if (expectedFieldValue instanceof Long) {
                actualValue = this.response.extract().jsonPath().getLong(fieldPath);
            } else if (expectedFieldValue instanceof Integer) {
                actualValue = this.response.extract().jsonPath().getInt(fieldPath);
            } else {
                actualValue = this.response.extract().jsonPath().getString(fieldPath);
            }

        } else {
            actualValue = this.response.extract().jsonPath().getString(fieldPath);
        }

        String messageIfFailed = String.format("Actual value '%s' is not equals to expected '%s' for the path '%s' and response: \n%s",
                actualValue,
                expectedFieldValue,
                fieldPath,
                this.response.extract().response().andReturn().asPrettyString());

        assertThat(actualValue).as(messageIfFailed).isEqualTo(expectedFieldValue);
        return this;
    }

    @Step("Check json value is not null")
    public ApiHttpResponse jsonValueIsNotNull(String fieldPath) {
        String actualValue = this.response.extract().jsonPath().getString(fieldPath);
        assertThat(actualValue).isNotNull();
        return this;
    }

    @Step("Check json value is null")
    public ApiHttpResponse jsonValueIsNull(String fieldPath) {
        String actualValue = this.response.extract().jsonPath().getString(fieldPath);
        assertThat(actualValue).isNull();
        return this;
    }

    @Step("Check that message contains: {expectedMessage}")
    public ApiHttpResponse jsonMessageContains(String expectedMessage) {
        String actualFullMessage = this.response.extract().jsonPath().getString("message");
        assertThat(actualFullMessage).contains(expectedMessage);
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
        return String.format("Status code: %s and response: \n%s",
                response.extract().response().statusCode(),
                response.extract().response().asPrettyString());
    }
}
