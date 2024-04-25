package unifor.devweb.project.freelearn.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class StudentCourseDTO implements Serializable {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @JsonProperty("studentId")
    @NotNull(message = "Student ID cannot be null")
    private Long studentId;

    @JsonProperty("courseId")
    @NotNull(message = "Course ID cannot be null")
    private Long courseId;
}
