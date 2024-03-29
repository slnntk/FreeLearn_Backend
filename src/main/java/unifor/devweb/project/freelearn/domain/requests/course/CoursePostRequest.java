package unifor.devweb.project.freelearn.domain.requests.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
public class CoursePostRequest {
    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("language")
    private String language;

    @JsonProperty("durationHours")
    private int durationHours;

    @JsonProperty("link")
    private String link;

    @JsonProperty("courseCategoryIds")
    private List<Long> courseCategoryIds;

    @JsonProperty("enrolledStudentIds")
    private List<Long> enrolledStudentIds;

    @JsonProperty("teacherId")
    private Long teacherId;

    @JsonProperty("moduleIds")
    private List<Long> moduleIds;
}
