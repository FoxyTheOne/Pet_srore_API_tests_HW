package constants;

import models.Gender;
import models.hw15_fluent.SuperheroWithBuilder;

public class SuperheroConstants {
    public static final String BASE_URL = "https://superhero.qa-test.csssr.com/";

    public static final SuperheroWithBuilder DEFAULT_SUPERMAN_SUPERHERO = SuperheroWithBuilder.builder()
            .birthDate("1938-06-01")
            .city("Krypton")
            .fullName("Superman")
            .gender(Gender.M)
            .id(160)
            .mainSkill("Flight")
            .phone("+7777777777")
            .build();

    public static final SuperheroWithBuilder BAD_REQUEST_400_SUPERHERO = SuperheroWithBuilder.builder()
            .birthDate("1938-02-29")
            .city("Krypton")
            .fullName("Superman")
            .gender(Gender.M)
            .id(170)
            .mainSkill("Flight")
            .phone("+7777777777")
            .build();

//    {
//        "birthDate": "1938-06-01",
//            "city": "Krypton",
//            "fullName": "Superman",
//            "gender": "M",
//            "id": 50,
//            "mainSkill": ""Flight"",
//            "phone": "+7777777777"
//    }

    public static final SuperheroWithBuilder EMPTY_SUPERMAN_SUPERHERO = SuperheroWithBuilder.builder()
            .fullName("Superman")
            .build();

    public static final SuperheroWithBuilder MINIMUM_SUPERMAN_SUPERHERO = SuperheroWithBuilder.builder()
            .birthDate("1938-06-01")
            .city("Krypton")
            .fullName("Superman")
            .gender(Gender.M)
            .id(180)
            .mainSkill("Flight")
            .build();

    public static final SuperheroWithBuilder UPDATE_BATMAN_SUPERHERO = SuperheroWithBuilder.builder()
            .birthDate("1972-02-19")
            .city("Gotham City")
            .fullName("Batman")
            .gender(Gender.M)
            .id(190)
            .mainSkill("Money")
            .phone("+5555555555")
            .build();
}
