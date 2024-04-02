package unifor.devweb.project.freelearn.domain.requests.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CoursePutRequest extends CourseRequest {

    @JsonProperty("courseCategoryIds")
    private List<Long> courseCategoryIds;

    @JsonProperty("enrolledStudentIds")
    private List<Long> enrolledStudentIds;

    @JsonProperty("moduleIds")
    private List<Long> moduleIds;

    @Override
    public String toString() {
        String superString = super.toString();

        return superString + "CoursePutRequest{" +
                "courseCategoryIds=" + courseCategoryIds +
                ", enrolledStudentIds=" + enrolledStudentIds +
                ", moduleIds=" + moduleIds +
                '}';
    }

}
