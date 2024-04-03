package unifor.devweb.project.freelearn.domain.requests.coursemodule;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Data
public class CourseModuleRequest {

    @Setter
    @JsonProperty("title")
    private String title;

    @Setter
    @JsonProperty("description")
    private String description;

    @Setter
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
