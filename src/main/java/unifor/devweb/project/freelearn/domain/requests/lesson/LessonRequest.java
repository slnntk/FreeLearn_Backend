package unifor.devweb.project.freelearn.domain.requests.lesson;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
public class LessonRequest {

    @Setter
    @JsonProperty("title")
    private String title;

    @Setter
    @JsonProperty("videoUrl")
    private String videoUrl;

    @Setter
    @JsonProperty("durationMinutes")
    private int durationMinutes;

    @JsonProperty("moduleId")
    private Long moduleId;



}
