package unifor.devweb.project.freelearn.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = StudentCourseDTO.class)
public class StudentCourseDTO implements Serializable {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @JsonProperty("studentId")
    private Long studentId;

    @JsonProperty("courseId")
    private Long courseId;
}
