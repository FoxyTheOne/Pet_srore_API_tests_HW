package apiTests.hw15_fluent;

import models.hw15_fluent.SuperheroWithBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static constants.SuperheroConstants.*;

class SuperheroFluentTests extends BaseSuperheroFluentTests {
    // CREATE
    @Test
    @Tag("smoke")
    @DisplayName("Create a new superhero and check status 200 ok")
    void addNewSuperheroTest() {
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(DEFAULT_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));
        ids.add(id);
    }

    @Test
    @Tag("smoke")
    @DisplayName("Create a new superhero with wrong data and check status 400 bad request")
    void addNewBrokenSuperheroTest() {
        superheroControllerFluent.createSuperhero(BAD_REQUEST_400_SUPERHERO)
                .statusCodeIs(400)
                .jsonValueIsNotNull("message")
                .jsonMessageContains("not a leap year");
    }

    @Test
    @Tag("smoke")
    @DisplayName("Create empty superhero and check status 403")
    void addEmptySuperheroTest() {
        superheroControllerFluent.createSuperhero(SuperheroWithBuilder.builder().build())
                .statusCodeIs(403)
                .jsonValueIsNotNull("message")
                .jsonMessageContains("Incorrect");
    }

    @Test
    @Tag("smoke")
    @DisplayName("Create superhero without data and check status 403")
    void addSuperheroWithoutDataTest() {
        superheroControllerFluent.createSuperhero(EMPTY_SUPERMAN_SUPERHERO)
                .statusCodeIs(403)
                .jsonValueIsNotNull("message")
                .jsonMessageContains("Incorrect");
    }

    @Test
    @Tag("smoke")
    @DisplayName("Create a new superhero with minimum fields and check status 200 ok")
    void addNotFullSuperheroTest() {
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(MINIMUM_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));
        ids.add(id);
    }

    // GET
    @Test
    @Tag("smoke")
    @DisplayName("Get all superheroes")
    void getAllSuperheroesTest() {
        superheroControllerFluent.getAllSuperheroes()
                .statusCodeIs(200);
    }

    @Test
    @Tag("smoke")
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

    @Test
    @Tag("smoke")
    @DisplayName("Get superhero by id, check status code and response body")
    void addAndGetSuperheroTest() throws InterruptedException {
        // Создадим супергероя и узнаем, какой ему присвоен id
        // Обычно это работает так: если заданный id сыободен, то возможно он будет присвоен объекту. Если же он занят, то генерируется и присваивается новый id
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(DEFAULT_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));

        // Сохраним полученный id для последующей очистки тестовых данных, которые мы создали во время тестов
        ids.add(id);

        // Проверим, можно ли найти созданного только что супергероя по сохраненному id
        Thread.sleep(3000);
        superheroControllerFluent.getSuperheroesById(id)
                .statusCodeIs(200)
                .jsonValueIs("birthDate", DEFAULT_SUPERMAN_SUPERHERO.getBirthDate())
                .jsonValueIs("city", DEFAULT_SUPERMAN_SUPERHERO.getCity())
                .jsonValueIs("fullName", DEFAULT_SUPERMAN_SUPERHERO.getFullName())
                .jsonValueIs("gender", String.valueOf(DEFAULT_SUPERMAN_SUPERHERO.getGender()))
                .jsonValueIs("id", id)
                .jsonValueIs("mainSkill", DEFAULT_SUPERMAN_SUPERHERO.getMainSkill())
                .jsonValueIs("phone", DEFAULT_SUPERMAN_SUPERHERO.getPhone());
    }

    // GET with retry
    @Test
    @Tag("with retry")
    @DisplayName("Get superhero with retry by id, check status code")
    void getSuperheroByIdWithRetryTest() throws InterruptedException {
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(DEFAULT_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));

        ids.add(id);

        Thread.sleep(3000);
        superheroControllerFluent.getSuperheroesByIdWithRetry(id)
                .statusCodeIs(200);
    }

    @Test
    @Tag("with retry")
    @DisplayName("Get superhero with retry by id, check status code and response body")
    void addAndGetSuperheroWithRetryTest() throws InterruptedException {
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(DEFAULT_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));

        ids.add(id);

        Thread.sleep(3000);
        superheroControllerFluent.getSuperheroesByIdWithRetry(id)
                .statusCodeIs(200)
                .jsonValueIs("birthDate", DEFAULT_SUPERMAN_SUPERHERO.getBirthDate())
                .jsonValueIs("city", DEFAULT_SUPERMAN_SUPERHERO.getCity())
                .jsonValueIs("fullName", DEFAULT_SUPERMAN_SUPERHERO.getFullName())
                .jsonValueIs("gender", String.valueOf(DEFAULT_SUPERMAN_SUPERHERO.getGender()))
                .jsonValueIs("id", id)
                .jsonValueIs("mainSkill", DEFAULT_SUPERMAN_SUPERHERO.getMainSkill())
                .jsonValueIs("phone", DEFAULT_SUPERMAN_SUPERHERO.getPhone());
    }

    // UPDATE
    @Test
    @Tag("with retry")
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

    // UPDATE with retry
    @Test
    @Tag("with retry")
    @DisplayName("Update superhero with retry by id")
    void updateSuperheroWithRetryByIdTest() throws InterruptedException {
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(DEFAULT_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));

        ids.add(id);

        Thread.sleep(3000);
        superheroControllerFluent.updateSuperheroWithRetry(UPDATE_BATMAN_SUPERHERO, id)
                .statusCodeIs(200);
    }

    // DELETE
    @Test
    @Tag("smoke")
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
