package unifor.devweb.project.freelearn.domain.requests.coursemodule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseModuleRequest {

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("sequenceNumber")
    private int sequenceNumber;

    @JsonProperty("courseId")
    private Long courseId;

    @Override
    public String toString() {
        return "CourseModuleRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                ", courseId=" + courseId +
                '}';
    }

}
