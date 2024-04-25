package unifor.devweb.project.freelearn.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
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
    private List<CourseCourseCategoryDTO> courseCategoryDTOS = new ArrayList<>();

    @JsonProperty("students")
    @JsonManagedReference
    private List<StudentCourseDTO> studentCourseDTOS = new ArrayList<>();

    @JsonProperty("teacher")
    @JsonManagedReference
    private TeacherDTO teacherDTO;

    @JsonProperty("modules")
    @JsonManagedReference
    private List<CourseModuleDTO> moduleDTOList = new ArrayList<>();
}
