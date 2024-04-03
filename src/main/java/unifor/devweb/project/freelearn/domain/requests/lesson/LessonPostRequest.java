package unifor.devweb.project.freelearn.domain.requests.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Setter
@Getter
@Data
public class LessonPostRequest extends LessonRequest{

    @JsonProperty("moduleId")
    private Long moduleId;

}
