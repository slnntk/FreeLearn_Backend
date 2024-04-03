package unifor.devweb.project.freelearn.domain.requests.lesson;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Data
public class LessonGetRequest extends LessonRequest{

    @JsonIgnore
    @JsonProperty("id")
    private Long id;

    @JsonProperty("moduleId")
    private Long moduleId;

    @JsonProperty("courseId")
    private Long courseId;

}
