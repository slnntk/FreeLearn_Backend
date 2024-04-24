package unifor.devweb.project.freelearn.dto;

import lombok.Data;

import java.util.List;

@Data
public class TeacherDTO {
    private Long id;
    private String employeeId;
    private List<String> areasOfExpertise;
    private double overallRating;
    private int hoursTaught;
    private Long userId;
    private List<Long> courseIds;
}
