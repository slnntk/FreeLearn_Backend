package unifor.devweb.project.freelearn.domain.requests.coursecategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Getter
@Setter
public class CourseCategoryPutRequest extends CourseCategoryRequest {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

    @JsonProperty("coursesId")
    private List<Long> coursesId;

}
