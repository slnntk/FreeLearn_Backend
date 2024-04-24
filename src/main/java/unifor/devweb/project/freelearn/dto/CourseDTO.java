package unifor.devweb.project.freelearn.dto;

import lombok.Data;
import org.springframework.lang.Nullable;

import java.util.List;

@Data
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String language;
    private int durationHours;
    private String link;

    @Nullable
    private List<Long> courseCategoryIds;

    @Nullable
    private List<Long> enrolledStudentIds;

    private Long teacherId;

    @Nullable
    private List<Long> moduleIds;
}