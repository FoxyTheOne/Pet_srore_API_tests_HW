package apiTests;

import controller.UserControllerFluent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static constants.CommonConstants.DEFAULT_STRING_USER_BUILDER;

class BaseUserFluentTests {
    // given().header("accept", "application/json").header("Content-Type", "application/json").baseUri("https://petstore.swagger.io") + .filter(new AllureRestAssured())
    UserControllerFluent userControllerFluent = new UserControllerFluent();

    // Если вы хотите создавать username в тестах, а он уже создан, то логично и в Before, и в AfterEach удалять его
    @BeforeEach
    @AfterEach
    void clear(){
        userControllerFluent.deleteUserByName(DEFAULT_STRING_USER_BUILDER.getUsername());
    }
}
