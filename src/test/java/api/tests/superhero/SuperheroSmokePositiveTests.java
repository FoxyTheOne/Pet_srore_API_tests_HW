package api.tests.superhero;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static constants.CommonConstants.*;
import static constants.SuperheroConstants.*;

@Tags({@Tag(SMOKE_TAG), @Tag(API_TAG), @Tag(POSITIVE_TAG)})
class SuperheroSmokePositiveTests extends BaseSuperheroFluentTests {
    @Test
    @Tag(CREATE_TAG)
    @DisplayName("Create a new superhero with ALL fields and check status 200 ok")
    void addNewSuperheroTest() {
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(DEFAULT_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));
        ids.add(id);
    }

    @Test
    @Tag(CREATE_TAG)
    @DisplayName("Create a new superhero with MINIMUM fields and check status 200 ok")
    void addNotFullSuperheroTest() {
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(MINIMUM_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));
        ids.add(id);
    }

    @Test
    @Tag(GET_TAG)
    @DisplayName("Get all superheroes")
    void getAllSuperheroesTest() {
        superheroControllerFluent.getAllSuperheroes()
                .statusCodeIs(200);
    }

    // UNSTABLE_TAG: Нужный объект возвращается не всегда, иногда его не находит (Expected status code <200> but was <400>)
    @Test
    @Tags({@Tag(GET_TAG), @Tag(UNSTABLE_TAG)})
    @DisplayName("Get superhero by id, check status code")
    void getSuperheroByIdTest() throws InterruptedException {
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(DEFAULT_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));

        ids.add(id);

        Thread.sleep(3000);
        superheroControllerFluent.getSuperheroesById(id)
                .statusCodeIs(200);
    }

    // UNSTABLE_TAG: Не стабильный тест, update работает не всегда (Expected status code <200> but was <400>)
    @Test
    @Tags({@Tag(UPDATE_TAG), @Tag(UNSTABLE_TAG)})
    @DisplayName("Update superhero by id")
    void updateSuperheroByIdTest() throws InterruptedException {
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(DEFAULT_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));

        ids.add(id);

        Thread.sleep(3000);
        superheroControllerFluent.updateSuperhero(UPDATE_BATMAN_SUPERHERO, id)
                .statusCodeIs(200);
    }

    // UNSTABLE_TAG: Не стабильный тест, update работает не всегда и нужно несколько попыток
    @Test
    @Tags({@Tag(UPDATE_TAG), @Tag(UNSTABLE_TAG), @Tag(WITH_RETRY_TAG)})
    @DisplayName("Update superhero with retry by id WITH RETRY")
    void updateSuperheroWithRetryByIdTest() throws InterruptedException {
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(DEFAULT_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));

        ids.add(id);

        Thread.sleep(3000);
        superheroControllerFluent.updateSuperheroWithRetry(UPDATE_BATMAN_SUPERHERO, id)
                .statusCodeIs(200);
    }

    @Test
    @Tag(DELETE_TAG)
    @DisplayName("Delete superhero")
    void deleteSuperheroByIdTest() throws InterruptedException {
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(DEFAULT_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));

        ids.add(id);

        Thread.sleep(3000);
        superheroControllerFluent.deleteSuperheroById(id)
                .statusCodeIs(200);
    }
}
