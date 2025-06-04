package api.controller.petstore.user;

import api.models.ApiHttpResponse;
import api.models.petstore.user.UserWithBuilder;

public interface IUserController {
    // CREATE - post
    ApiHttpResponse createUser(UserWithBuilder user);

    ApiHttpResponse createUserWithRetry(UserWithBuilder user) throws InterruptedException;

    // GET - get
    ApiHttpResponse getUserByUsername(String username);

    // UPDATE - put
    ApiHttpResponse updateUser(UserWithBuilder user, String username);

    // DELETE - delete
    ApiHttpResponse deleteUserByName(String username);
}
