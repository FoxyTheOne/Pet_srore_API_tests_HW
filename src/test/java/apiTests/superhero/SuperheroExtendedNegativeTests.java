package apiTests.superhero;

import models.SuperheroWithBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static constants.CommonConstants.*;
import static constants.SuperheroConstants.BAD_REQUEST_400_SUPERHERO;
import static constants.SuperheroConstants.EMPTY_SUPERMAN_SUPERHERO;

@Tags({@Tag(EXTENDED_TAG), @Tag(API_TAG), @Tag(NEGATIVE_TAG)})
class SuperheroExtendedNegativeTests extends BaseSuperheroFluentTests {
    @Test
    @Tag(CREATE_TAG)
    @DisplayName("Create a new superhero with WRONG data and check status 400 bad request")
    void addNewBrokenSuperheroTest() {
        superheroControllerFluent.createSuperhero(BAD_REQUEST_400_SUPERHERO)
                .statusCodeIs(400)
                .jsonValueIsNotNull("message")
                .jsonMessageContains("not a leap year");
    }

    @Test
    @Tag(CREATE_TAG)
    @DisplayName("Create EMPTY superhero and check status 403")
    void addEmptySuperheroTest() {
        superheroControllerFluent.createSuperhero(SuperheroWithBuilder.builder().build())
                .statusCodeIs(403)
                .jsonValueIsNotNull("message")
                .jsonMessageContains("Incorrect");
    }

    @Test
    @Tag(CREATE_TAG)
    @DisplayName("Create superhero with ONLY name and check status 403")
    void addSuperheroWithoutDataTest() {
        superheroControllerFluent.createSuperhero(EMPTY_SUPERMAN_SUPERHERO)
                .statusCodeIs(403)
                .jsonValueIsNotNull("message")
                .jsonMessageContains("Incorrect");
    }
}
