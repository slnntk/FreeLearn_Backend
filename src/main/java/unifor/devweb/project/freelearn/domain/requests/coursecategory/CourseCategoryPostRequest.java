package unifor.devweb.project.freelearn.domain.requests.coursecategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Data
public class CourseCategoryPostRequest extends CourseCategoryRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("coursesId")
    private List<Long> coursesId;

}
