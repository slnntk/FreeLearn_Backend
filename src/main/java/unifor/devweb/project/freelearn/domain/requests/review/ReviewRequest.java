package unifor.devweb.project.freelearn.domain.requests.review;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
public class ReviewRequest {

    @Setter
    @JsonProperty("comment")
    private String comment;

    @JsonProperty("studentId")
    private Long studentId;

    @JsonProperty("courseId")
    private Long courseId;

    @JsonProperty("teacherId")
    private Long teacherId;
}
