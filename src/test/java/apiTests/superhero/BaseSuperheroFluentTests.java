package apiTests.superhero;

import controller.SuperheroControllerFluent;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class BaseSuperheroFluentTests {
    SuperheroControllerFluent superheroControllerFluent = new SuperheroControllerFluent();
    List<Integer> ids = new ArrayList<>();

    @BeforeEach
    @AfterEach
    void clear() {
        for (int id : ids) {
            superheroControllerFluent.deleteSuperheroById(id);
        }
    }
}
