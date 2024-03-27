package unifor.devweb.project.freelearn.requests.Course;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class CoursePostRequestBody {
    private String title;
    private String description;
    private String imageUrl;
    private String language;
    private int durationHours;
    private String link;
    private List<Long> courseCategoryIds;
    private Long teacherId;
    private List<Long> moduleIds;
}
