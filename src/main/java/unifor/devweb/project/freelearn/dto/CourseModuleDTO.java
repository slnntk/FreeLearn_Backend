package unifor.devweb.project.freelearn.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CourseModuleDTO.class)
public class CourseModuleDTO implements Serializable {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotNull(message = "Sequence Number cannot be null")
    private int sequenceNumber;

    @JsonProperty("course")
    @JsonIdentityReference(alwaysAsId = true)
    private CourseDTO course;

    @JsonProperty("lessons")
    private List<LessonDTO> lessonDTOS;
}
