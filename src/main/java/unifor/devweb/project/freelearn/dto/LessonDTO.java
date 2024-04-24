package unifor.devweb.project.freelearn.dto;

import lombok.Data;

@Data
public class LessonDTO {
    private Long id;
    private String title;
    private String videoUrl;
    private int durationMinutes;
    private Long moduleId;
}
