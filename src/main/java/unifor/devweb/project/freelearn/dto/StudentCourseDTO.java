package unifor.devweb.project.freelearn.dto;

import lombok.Data;

@Data
public class StudentCourseDTO {
    private Long id;
    private Long studentId;
    private Long courseId;
}

