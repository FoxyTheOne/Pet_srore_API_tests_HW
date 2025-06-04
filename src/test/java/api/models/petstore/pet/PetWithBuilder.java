package api.models.petstore.pet;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetWithBuilder {
    private int id;
    private PetCategoryWithBuilder category;
    private String name;
    private List<String> photoUrls;
    private List<PetTagWithBuilder> tags;
    private PetStatus status;
}
