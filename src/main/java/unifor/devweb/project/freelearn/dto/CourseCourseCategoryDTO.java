package unifor.devweb.project.freelearn.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CourseCourseCategoryDTO.class)
public class CourseCourseCategoryDTO implements Serializable {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @JsonProperty("courseId")
    private Long courseId;
    @JsonProperty("categoryId")
    private Long categoryId;
}
