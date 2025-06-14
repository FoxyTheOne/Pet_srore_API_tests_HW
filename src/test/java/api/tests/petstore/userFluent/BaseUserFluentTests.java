package api.tests.petstore.userFluent;

import api.controller.petstore.user.UserControllerFluent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import static constants.PetStoreConstants.DEFAULT_STRING_USER_BUILDER;
import static constants.PetStoreConstants.STRING2_USER_BUILDER;

class BaseUserFluentTests {
    // given().header("accept", "application/json").header("Content-Type", "application/json").baseUri("https://petstore.swagger.io") + .filter(new AllureRestAssured())
    UserControllerFluent userControllerFluent = new UserControllerFluent();

    // Если вы хотите создавать username в тестах, а он уже создан, то логично и в Before, и в AfterEach удалять его
    @BeforeEach
    @AfterEach
    void clear(){
        userControllerFluent.deleteUserByName(DEFAULT_STRING_USER_BUILDER.getUsername());
        userControllerFluent.deleteUserByName(STRING2_USER_BUILDER.getUsername());
    }
}
