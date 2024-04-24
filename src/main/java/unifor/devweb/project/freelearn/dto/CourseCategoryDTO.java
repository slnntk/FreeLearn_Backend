package unifor.devweb.project.freelearn.dto;

import lombok.Data;
import java.util.List;

@Data
public class CourseCategoryDTO {
    private Long id;
    private String name;
    private String description;
    private List<CourseDTO> courses;
}
