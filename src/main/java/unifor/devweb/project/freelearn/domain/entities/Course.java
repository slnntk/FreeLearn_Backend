package unifor.devweb.project.freelearn.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private String imageUrl;
    private String language;
    private int durationHours;
    private String link;

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private List<CourseCategory> courseCategories;

    @ManyToMany(mappedBy = "enrolledCourses")
    private List<Student> enrolledStudents;

    @ManyToOne
    private Teacher teacher;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private List<CourseModule> modules;
}
