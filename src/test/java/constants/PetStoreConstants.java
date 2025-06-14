package constants;

import api.models.hw14.User;
import api.models.petstore.user.UserWithBuilder;

public class PetStoreConstants {
    public static final String BASE_URL = "https://petstore.swagger.io";

    // PET
    public static final String PET_URL = "/v2/pet";
    public static final String UPLOAD_IMAGE_URL = "/uploadImage";
    public static final String BEE_PATH_NAME = "src/main/resources/bee.jpg";

    // USER
    public static final String USER_URL = "/v2/user";

    public static final User DEFAULT_STRING_USER = new User(0, "string", "string", "string", "string", "string", "string", 0);

    public static final UserWithBuilder DEFAULT_STRING_USER_BUILDER = UserWithBuilder.builder()
            .id(0)
            .username("string")
            .firstName("string")
            .lastName("string")
            .email("string")
            .password("string")
            .phone("string")
            .userStatus(0)
            .build();

    public static User expectedUser = new User(9223372036854767242L, "string", "string", "string", "string", "string", "string", 0);

    public static final User STRING2_USER = new User(1, "string2", "string2", "string2", "string2", "string2", "string2", 1);

    public static final UserWithBuilder STRING2_USER_BUILDER = UserWithBuilder.builder()
            .id(1)
            .username("string2")
            .firstName("string2")
            .lastName("string2")
            .email("string2")
            .password("string2")
            .phone("string2")
            .userStatus(1)
            .build();
}
