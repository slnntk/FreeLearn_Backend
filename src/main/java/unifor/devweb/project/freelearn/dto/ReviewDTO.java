package unifor.devweb.project.freelearn.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class ReviewDTO implements Serializable {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "Comment cannot be blank")
    private String comment;

    @JsonProperty("studentId")
    @NotNull(message = "Student id cannot be null")
    private Long studentId;

    @JsonProperty("courseId")
    @NotNull(message = "Course id cannot be null")
    private Long courseId;

    @JsonProperty("teacherId")
    @NotNull(message = "Teacher id cannot be null")
    private Long teacherId;
}
