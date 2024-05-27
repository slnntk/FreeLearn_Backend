package unifor.devweb.project.freelearn.dto;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = TeacherDTO.class)
public class TeacherDTO implements Serializable {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "Employee ID cannot be blank")
    private String employeeId;

    private List<String> areasOfExpertise;

    @NotNull(message = "Overall Rating cannot be null")
    private double overallRating;

    @NotNull(message = "Hours Taught cannot be null")
    private int hoursTaught;

    @JsonProperty("user")
    @JsonIdentityReference(alwaysAsId = true)
    private UserDTO userDTO;

    @JsonProperty("courses")
    @JsonIdentityReference(alwaysAsId = true)
    private List<CourseDTO> courseDTOList;
}
