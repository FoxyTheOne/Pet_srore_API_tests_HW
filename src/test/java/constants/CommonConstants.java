package constants;

import models.User;

public class CommonConstants {
    public static final String BASE_URL = "https://petstore.swagger.io";

    public static final User DEFAULT_USER = new User(0, "string", "string","string","string","string","string", 0);

    public static final String USERNAME_STRING = "string";

    public static User expectedUser = new User(9223372036854767242L, "string", "string","string","string","string","string", 0);

    public static final User STRING2_USER = new User(1, "string2", "string2","string2","string2","string2","string2", 1);

    public static final String USERNAME_STRING2 = "string2";
}
