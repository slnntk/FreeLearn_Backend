package unifor.devweb.project.freelearn.dto;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = CourseDTO.class)
public class CourseDTO implements Serializable {
    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    private String title;

    @NotBlank(message = "Description cannot be blank")
    private String description;

    @NotBlank(message = "Image URL cannot be blank")
    private String imageUrl;

    @NotBlank(message = "Language cannot be blank")
    private String language;

    @NotNull(message = "Duration cannot be null")
    private int durationHours;

    @NotBlank(message = "Link cannot be blank")
    private String link;


    @JsonProperty("categories")
    private List<CourseCourseCategoryDTO> courseCategories;

    @JsonProperty("students")
    private List<StudentCourseDTO> studentCourseDTOS;

    @JsonProperty("teacher")
    @JsonIdentityReference(alwaysAsId = true)
    private TeacherDTO teacherDTO;

    @JsonProperty("modules")
    private List<CourseModuleDTO> moduleDTOList;
}
