package unifor.devweb.project.freelearn.dto;

import lombok.Data;
import java.util.List;

@Data
public class StudentDTO {
    private Long id;
    private int hoursWatched;
    private int numberOfCoursesSubscribed;
    private Long userId;
    private List<Long> enrolledCourseIds;
    private List<Long> courseReviewIds;
}
