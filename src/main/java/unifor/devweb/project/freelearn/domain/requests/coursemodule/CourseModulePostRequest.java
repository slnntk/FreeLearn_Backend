package unifor.devweb.project.freelearn.domain.requests.coursemodule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EqualsAndHashCode(callSuper = true)
@Data
public class CourseModulePostRequest extends CourseModuleRequest{

    @JsonProperty("courseId")
    private Long courseId;

}
