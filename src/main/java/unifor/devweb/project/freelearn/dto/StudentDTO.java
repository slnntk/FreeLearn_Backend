package unifor.devweb.project.freelearn.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
public class StudentDTO implements Serializable {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "Hours Watched cannot be null")
    private int hoursWatched;

    @NotNull(message = "Number of Courses Subscribed cannot be null")
    private int numberOfCoursesSubscribed;

    @JsonProperty("user")
    @NotNull(message = "User cannot be null")
    private UserDTO userDTO;

    @JsonProperty("courses")
    private List<StudentCourseDTO> studentCourseDTOList;

    @JsonProperty("reviews")
    private List<ReviewDTO> reviewDTOList;
}
