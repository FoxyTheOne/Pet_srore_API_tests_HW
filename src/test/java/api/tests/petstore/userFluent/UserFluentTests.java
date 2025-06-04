package api.tests.petstore.userFluent;

import api.models.ApiHttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static constants.PetStoreConstants.*;

class UserFluentTests extends BaseUserFluentTests {

    // given() у нас находится в BaseUserFluentTests
    // В Before и AfterEach мы удаляем дефолтного user, поэтому сделаем первым тест, который добавляет его и проверяет его статус код
    @Test
    @Tag("smoke")
    @DisplayName("Check that create new user returns ok status 200")
    void simpleCreateUserTest() {
        // .when().body(body).post("/v2/user")
        ApiHttpResponse response = userControllerFluent.createUser(DEFAULT_STRING_USER_BUILDER);

        response.statusCodeIs(200);
    }

    // В нашем случае метод POST на сайте добавляет user сколько угодно раз, поэтому мы можем POST такого же user ещё раз
    // А в конкретно нашей реализации дефолтный user удаляется после каждого теста, поэтому его так же можно создавать сколько угодно раз
    @Test
    @Tag("smoke")
    @DisplayName("Check that create new user returns ok status 200 and fields are not null")
    void createUserTest() {
        // .when().body(body).post("/v2/user")
        ApiHttpResponse response = userControllerFluent.createUser(DEFAULT_STRING_USER_BUILDER);


        // Проверка Response body
        String messageValue = response
                .statusCodeIs(200)
                .jsonValueIsNotNull("message")
                .jsonValueIs("type", "unknown")
                .getJsonValueAndCheckIsNotNull("message");
    }

    // В нашем случае метод POST на сайте добавляет user сколько угодно раз, поэтому мы можем POST такого же user ещё раз
    // А в конкретно нашей реализации дефолтный user удаляется после каждого теста, поэтому его так же можно создавать сколько угодно раз
    @Test
    @Tag("with retry")
    @DisplayName("Check with retry that create new user returns ok status 200 and fields are not null")
    void createUserWithRetryTest() throws InterruptedException {
        // .when().body(body).post("/v2/user")
        ApiHttpResponse response = userControllerFluent.createUserWithRetry(DEFAULT_STRING_USER_BUILDER);

        // Проверка Response body
        String messageValue = response
                .statusCodeIs(200)
                .jsonValueIsNotNull("message")
                .jsonValueIs("type", "unknown")
                .getJsonValueAndCheckIsNotNull("message");
    }

    @Test
    @Tag("smoke")
    @DisplayName("Check that get user by username returns ok status 200, fields are not null and username is equal")
    void getUserByUsernameTest() {
        ApiHttpResponse user = userControllerFluent.getUserByUsername(DEFAULT_STRING_USER_BUILDER.getUsername());

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
                .jsonValueIs("username", DEFAULT_STRING_USER_BUILDER.getUsername());
    }

    @Test
    @Tag("smoke")
    @DisplayName("Check that update user returns ok status 200 and fields are not null")
    void updateUserTest() {
        ApiHttpResponse response = userControllerFluent.updateUser(STRING2_USER_BUILDER, STRING2_USER_BUILDER.getUsername());

        response
                .statusCodeIs(200)
                .jsonValueIsNotNull("type")
                .jsonValueIsNotNull("message")
                .jsonValueIs("type", "unknown");
    }

    @Test
    @Tag("smoke")
    @DisplayName("Check that delete user returns ok status 200, fields are not null and message is equal to username")
    void deleteUserTest() {
        ApiHttpResponse response = userControllerFluent.deleteUserByName(DEFAULT_STRING_USER_BUILDER.getUsername());

        response
                .statusCodeIs(200)
                .jsonValueIsNotNull("type")
                .jsonValueIsNotNull("message")
                .jsonValueIs("type", "unknown")
                .jsonValueIs("message", DEFAULT_STRING_USER_BUILDER.getUsername());
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
                .jsonValueIs("username", DEFAULT_STRING_USER.getUsername())
                .jsonValueIsNotNull("firstName")
                .jsonValueIs("firstName", DEFAULT_STRING_USER.getFirstName())
                .jsonValueIsNotNull("lastName")
                .jsonValueIs("lastName", DEFAULT_STRING_USER.getLastName())
                .jsonValueIsNotNull("email")
                .jsonValueIs("email", DEFAULT_STRING_USER.getEmail())
                .jsonValueIsNotNull("password")
                .jsonValueIs("password", DEFAULT_STRING_USER.getPassword())
                .jsonValueIsNotNull("phone")
                .jsonValueIs("phone", DEFAULT_STRING_USER.getPhone())
                .jsonValueIsNotNull("userStatus")
                .jsonValueIs("userStatus", DEFAULT_STRING_USER.getUserStatus());
    }
}

