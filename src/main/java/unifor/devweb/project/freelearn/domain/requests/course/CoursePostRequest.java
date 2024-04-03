package unifor.devweb.project.freelearn.domain.requests.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CoursePostRequest extends CourseRequest{

    @JsonProperty("courseCategoryIds")
    private List<Long> courseCategoryIds;

    @JsonProperty("enrolledStudentIds")
    private List<Long> enrolledStudentIds;

    @Override
    public String toString() {
        String superString = super.toString();

        return superString + "CoursePostRequest{" +
                "courseCategoryIds=" + courseCategoryIds +
                ", enrolledStudentIds=" + enrolledStudentIds +
                '}';
    }

}
