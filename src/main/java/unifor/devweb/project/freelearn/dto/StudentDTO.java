package unifor.devweb.project.freelearn.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class StudentDTO implements Serializable {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "Hours Watched cannot be null")
    private int hoursWatched;

    @NotNull(message = "Number of Courses Subscribed cannot be null")
    private int numberOfCoursesSubscribed;

    @JsonProperty("user")
    @JsonIdentityReference(alwaysAsId = true)
    private UserDTO userDTO;

    @JsonProperty("courses")
    private List<StudentCourseDTO> studentCourseDTOList;

    @JsonProperty("reviews")
    @JsonIdentityReference(alwaysAsId = true)
    private List<ReviewDTO> reviewDTOList;
}
