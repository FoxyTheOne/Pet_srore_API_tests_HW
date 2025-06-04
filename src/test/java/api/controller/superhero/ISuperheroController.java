package api.controller.superhero;

import api.models.ApiHttpResponse;
import api.models.superhero.SuperheroWithBuilder;

public interface ISuperheroController {
    // CREATE - post
    ApiHttpResponse createSuperhero(SuperheroWithBuilder superhero);

    // GET - get
    ApiHttpResponse getSuperheroesById(Integer id);

    ApiHttpResponse getSuperheroesByIdWithRetry(Integer id) throws InterruptedException;

    ApiHttpResponse getAllSuperheroes();

    // UPDATE - put
    ApiHttpResponse updateSuperhero(SuperheroWithBuilder superhero, Integer id);

    ApiHttpResponse updateSuperheroWithRetry(SuperheroWithBuilder superhero, Integer id) throws InterruptedException;

    // DELETE - delete
    ApiHttpResponse deleteSuperheroById(Integer id);
}
