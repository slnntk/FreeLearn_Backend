package unifor.devweb.project.freelearn.domain.requests.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CourseGetRequest extends CourseRequest{

    @JsonIgnore
    @JsonProperty("id")
    private Long id;

    @JsonProperty("courseCategoryIds")
    private List<Long> courseCategoryIds;

    @JsonProperty("moduleIds")
    private List<Long> moduleIds;

    @Override
    public String toString() {
        String superString = super.toString();

        return superString + "CoursePutRequest{" +
                "courseCategoryIds=" + courseCategoryIds +
                ", moduleIds=" + moduleIds +
                '}';
    }

}
