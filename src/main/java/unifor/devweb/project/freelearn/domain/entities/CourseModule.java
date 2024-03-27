package unifor.devweb.project.freelearn.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unifor.devweb.project.freelearn.serialization.CustomCourseModuleSerialization;
import unifor.devweb.project.freelearn.serialization.CustomCourseSerialization;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = CustomCourseModuleSerialization.class)
public class CourseModule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private int sequenceNumber;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "module")
    private List<Lesson> lessons;

}
