package unifor.devweb.project.freelearn.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class LessonDTO implements Serializable {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Video URL cannot be blank")
    private String videoUrl;

    @NotNull(message = "Duration cannot be null")
    private int durationMinutes;

    @JsonProperty("module")
    @JsonIdentityReference(alwaysAsId = true)
    private CourseModuleDTO courseModule;
}
