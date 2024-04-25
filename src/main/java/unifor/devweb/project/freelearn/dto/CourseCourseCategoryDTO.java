package unifor.devweb.project.freelearn.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class CourseCourseCategoryDTO implements Serializable {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "Course ID cannot be null")
    private Long courseId;

    @NotNull(message = "Category ID cannot be null")
    private Long categoryId;
}
