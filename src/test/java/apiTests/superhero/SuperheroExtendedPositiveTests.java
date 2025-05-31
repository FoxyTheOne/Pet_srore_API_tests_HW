package apiTests.superhero;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import static constants.CommonConstants.*;
import static constants.SuperheroConstants.DEFAULT_SUPERMAN_SUPERHERO;

@Tags({@Tag(EXTENDED_TAG), @Tag(API_TAG), @Tag(POSITIVE_TAG)})
class SuperheroExtendedPositiveTests extends BaseSuperheroFluentTests {
    // UNSTABLE_TAG: Нужный объект возвращается не всегда, иногда его не находит и нужно несколько попыток
    @Test
    @Tags({@Tag(GET_TAG), @Tag(UNSTABLE_TAG), @Tag(WITH_RETRY_TAG)})
    @DisplayName("Get superhero by id, check status code WITH RETRY")
    void getSuperheroByIdWithRetryTest() throws InterruptedException {
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(DEFAULT_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));

        ids.add(id);

        Thread.sleep(3000);
        superheroControllerFluent.getSuperheroesByIdWithRetry(id)
                .statusCodeIs(200);
    }

    // UNSTABLE_TAG: Нужный объект возвращается не всегда, иногда его не находит
    // DEFECT_TAG: Если нужный объект находит, то у него поле "phone" всегда null
    @Test
    @Tags({@Tag(GET_TAG), @Tag(UNSTABLE_TAG), @Tag(DEFECT_TAG)})
    @DisplayName("Get superhero by id, check status code and response body")
    void addAndGetSuperheroTest() throws InterruptedException {
        // 1. Создадим супергероя и узнаем, какой ему присвоен id (если заданный id свободен, то возможно он будет присвоен объекту. Если же он занят, то генерируется и присваивается новый id)
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(DEFAULT_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));

        // 2. Сохраним полученный id для последующей очистки тестовых данных, которые мы создали во время тестов
        ids.add(id);

        // 3. Проверим, можно ли найти созданного только что супергероя по сохраненному id
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

    // UNSTABLE_TAG: Нужный объект возвращается не всегда, иногда его не находит
    // DEFECT_TAG: Если нужный объект находит, то у него поле "phone" всегда null
    @Test
    @Tags({@Tag(GET_TAG), @Tag(UNSTABLE_TAG), @Tag(DEFECT_TAG), @Tag(WITH_RETRY_TAG)})
    @DisplayName("Get superhero by id, check status code and response body WITH RETRY")
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

    // UNSTABLE_TAG: Удаление не всегда удаляет, даже если получен ответ от сервера 200.
    // После успешного удаления запрос get может вернуть только что удалённый объект (Expected status code <400> but was <200>)
    @Test
    @Tags({@Tag(DELETE_TAG), @Tag(UNSTABLE_TAG)})
    @DisplayName("Delete superhero")
    void deleteSuperheroByIdTest() throws InterruptedException {
        int id = Integer.parseInt(superheroControllerFluent.createSuperhero(DEFAULT_SUPERMAN_SUPERHERO)
                .statusCodeIs(200)
                .getJsonValueAndCheckIsNotNull("id"));

        ids.add(id);

        Thread.sleep(3000);
        superheroControllerFluent.deleteSuperheroById(id)
                .statusCodeIs(200);

        Thread.sleep(3000);
        superheroControllerFluent.getSuperheroesById(id)
                .statusCodeIs(400);
    }
}
