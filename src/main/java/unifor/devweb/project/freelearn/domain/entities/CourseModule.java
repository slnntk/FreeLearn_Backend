package unifor.devweb.project.freelearn.domain.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import unifor.devweb.project.freelearn.serialization.CustomCourseModuleSerialization;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = CustomCourseModuleSerialization.class)
@ToString
public class CourseModule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private int sequenceNumber;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "module")
    private List<Lesson> lessons;


    @Override
    public String toString() {
        return "CourseModule{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", sequenceNumber=" + sequenceNumber +
                '}';
    }
}
