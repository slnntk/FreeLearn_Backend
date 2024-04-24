package unifor.devweb.project.freelearn.domain.requests.coursecategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
@Data
public class CourseCategoryGetRequest extends CourseCategoryRequest {

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;

    @JsonProperty("coursesId")
    private List<Long> coursesId;

}
