package unifor.devweb.project.freelearn.domain.requests.coursemodule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.List;

@Getter
@EqualsAndHashCode(callSuper = true)
@Data
public class CourseModuleGetRequest extends CourseModuleRequest{

    @JsonIgnore
    @JsonProperty("id")
    private Long id;

    @JsonProperty("lessonsId")
    private List<Long> lessonsId;

}
