package api.controller.petstore.pet;

import api.models.ApiHttpResponse;

import java.io.File;

public interface IPetController {
    ApiHttpResponse uploadImageById(File file, int petId, String metadata);

//    ApiHttpResponse addANewPetToTheStore(PetWithBuilder pet);
//
//    ApiHttpResponse updateAnExistingPet(PetWithBuilder pet);

    // И далее остальные pet методы со страницы https://petstore.swagger.io/#/
    // Логично сначала составлять список в интерфейсе, чтобы потом сделать implements в соответствующем классе и ничего не забыть
}
