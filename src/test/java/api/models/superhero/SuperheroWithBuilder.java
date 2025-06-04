package api.models.superhero;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SuperheroWithBuilder {
    private String birthDate;
    private String city;
    private String fullName;
    private Gender gender;
    private Integer id;
    private String mainSkill;
    private String phone;
}
