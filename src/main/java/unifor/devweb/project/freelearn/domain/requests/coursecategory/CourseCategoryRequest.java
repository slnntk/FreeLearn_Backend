package unifor.devweb.project.freelearn.domain.requests.coursecategory;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
public class CourseCategoryRequest {

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;

    @JsonProperty("coursesId")
    private List<Long> coursesId;

    @Override
    public String toString() {
        return "CourseModuleRequest{" +
                "title='" + name + '\'' +
                ", description='" + description + '\'' +
                ", courseIds=" + coursesId +
                '}';
    }

}
