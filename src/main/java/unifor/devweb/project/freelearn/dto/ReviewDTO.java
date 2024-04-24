package unifor.devweb.project.freelearn.dto;

import lombok.Data;

@Data
public class ReviewDTO {
    private Long id;
    private String comment;
    private Long studentId;
    private Long courseId;
    private Long teacherId;
}
