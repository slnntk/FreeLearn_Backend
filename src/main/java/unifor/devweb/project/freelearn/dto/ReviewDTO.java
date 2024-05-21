package unifor.devweb.project.freelearn.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ReviewDTO implements Serializable {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "Comment cannot be blank")
    private String comment;

    private Double rating;

    @JsonProperty("student")
    @JsonIdentityReference(alwaysAsId = true)
    private StudentDTO student;

    @JsonProperty("course")
    @JsonIdentityReference(alwaysAsId = true)
    private CourseDTO course;

    @JsonProperty("teacher")
    @JsonIdentityReference(alwaysAsId = true)
    private TeacherDTO teacher;
}
