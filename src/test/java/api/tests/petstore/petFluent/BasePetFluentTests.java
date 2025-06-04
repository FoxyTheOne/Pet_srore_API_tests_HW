package api.tests.petstore.petFluent;

import api.controller.petstore.pet.PetController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

public class BasePetFluentTests {
    PetController petController = new PetController();
    List<Integer> ids = new ArrayList<>();

    @BeforeEach
    @AfterEach
    void clear() {
//        for (int id : ids) {
//            petController.deleteSuperheroById(id);
//        }
    }
}
