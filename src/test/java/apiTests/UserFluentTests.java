package apiTests;

import models.UserApiHttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static constants.CommonConstants.*;

class UserFluentTests extends BaseUserFluentTests {

    // given() у нас находится в BaseUserFluentTests
    // В Before и AfterEach мы удаляем дефолтного user, поэтому сделаем первым тест, который добавляет его и проверяет его статус код
    @Test
    @Tag("smoke")
    @DisplayName("Check that create user returns ok status 200")
    void simpleCreateUserTest() {
        // .when().body(body).post("/v2/user")
        UserApiHttpResponse response = userControllerFluent.createUser(DEFAULT_STRING_USER_BUILDER);

        response.statusCodeIs(200);
    }

    // В нашем случае метод POST на сайте добавляет user сколько угодно раз, поэтому мы можем POST такого же user ещё раз
    @Test
    @Tag("smoke")
    @DisplayName("Check that create user returns ok status 200 and fields are not null")
    void createUserTest() {
        // .when().body(body).post("/v2/user")
        UserApiHttpResponse response = userControllerFluent.createUser(DEFAULT_STRING_USER_BUILDER);


        // Проверка Response body
        String messageValue = response
                .statusCodeIs(200)
                .jsonValueIsNotNull("message")
                .jsonStringValueIs("type", "unknown")
                .getJsonValueAndCheckIsNotNull("message");
    }

    @Test
    @Tag("smoke")
    @DisplayName("Check that get user by username returns ok status 200, fields are not null and username is equal")
    void getUserByUsernameTest() {
        UserApiHttpResponse user = userControllerFluent.getUserByUsername(DEFAULT_STRING_USER_BUILDER.getUsername());

        user
                .statusCodeIs(200)
                .jsonValueIsNotNull("id")
                .jsonValueIsNotNull("username")
                .jsonValueIsNotNull("firstName")
                .jsonValueIsNotNull("lastName")
                .jsonValueIsNotNull("email")
                .jsonValueIsNotNull("password")
                .jsonValueIsNotNull("phone")
                .jsonValueIsNotNull("userStatus")
                .jsonStringValueIs("username", DEFAULT_STRING_USER_BUILDER.getUsername());
    }

    @Test
    @Tag("smoke")
    @DisplayName("Check that update user returns ok status 200 and fields are not null")
    void updateUserTest() {
        UserApiHttpResponse response = userControllerFluent.updateUser(STRING2_USER_BUILDER, STRING2_USER_BUILDER.getUsername());

        response
                .statusCodeIs(200)
                .jsonValueIsNotNull("type")
                .jsonValueIsNotNull("message")
                .jsonStringValueIs("type", "unknown");
    }

    @Test
    @Tag("smoke")
    @DisplayName("Check that delete user returns ok status 200, fields are not null and message is equal to username")
    void deleteUserTest() {
        UserApiHttpResponse response = userControllerFluent.deleteUserByName(DEFAULT_STRING_USER_BUILDER.getUsername());

        response
                .statusCodeIs(200)
                .jsonValueIsNotNull("type")
                .jsonValueIsNotNull("message")
                .jsonStringValueIs("type", "unknown")
                .jsonStringValueIs("message", DEFAULT_STRING_USER_BUILDER.getUsername());
    }


    @Test
    @Tag("extended")
    @DisplayName("Check user added correctly")
    void checkAddUserExtendedTest() {
        // 1. Создаём user
        String expectedId = userControllerFluent.createUser(DEFAULT_STRING_USER_BUILDER)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("message");

        // 2. Получаем с сервера созданный user
        userControllerFluent.getUserByUsername(DEFAULT_STRING_USER_BUILDER.getUsername())
                .statusCodeIs(200)
                .jsonValueIsNotNull("id")
                .jsonValueIsNotNull("username")
                .jsonStringValueIs("username", DEFAULT_STRING_USER.getUsername())
                .jsonValueIsNotNull("firstName")
                .jsonStringValueIs("firstName", DEFAULT_STRING_USER.getFirstName())
                .jsonValueIsNotNull("lastName")
                .jsonStringValueIs("lastName", DEFAULT_STRING_USER.getLastName())
                .jsonValueIsNotNull("email")
                .jsonStringValueIs("email", DEFAULT_STRING_USER.getEmail())
                .jsonValueIsNotNull("password")
                .jsonStringValueIs("password", DEFAULT_STRING_USER.getPassword())
                .jsonValueIsNotNull("phone")
                .jsonStringValueIs("phone", DEFAULT_STRING_USER.getPhone())
                .jsonValueIsNotNull("userStatus")
                .jsonLongValueIs("userStatus", DEFAULT_STRING_USER.getUserStatus());
    }
}

