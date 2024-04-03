package unifor.devweb.project.freelearn.domain.requests.coursemodule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import unifor.devweb.project.freelearn.domain.entities.Lesson;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CourseModulePutRequest extends CourseModuleRequest{

    @JsonProperty("lessonsId")
    private List<Long> lessonsId;

}
