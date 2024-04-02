package unifor.devweb.project.freelearn.domain.requests.course;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourseRequest {

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("language")
    private String language;

    @JsonProperty("durationHours")
    private int durationHours;

    @JsonProperty("link")
    private String link;

    @JsonProperty("teacherId")
    private Long teacherId;

    @Override
    public String toString() {
        return "CourseRequest{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", language='" + language + '\'' +
                ", durationHours=" + durationHours +
                ", link='" + link + '\'' +
                ", teacherId=" + teacherId +
                '}';
    }

}
