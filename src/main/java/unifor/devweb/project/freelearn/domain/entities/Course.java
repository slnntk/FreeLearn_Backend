package unifor.devweb.project.freelearn.domain.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import unifor.devweb.project.freelearn.serialization.CustomCourseSerialization;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = CustomCourseSerialization.class)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String imageUrl;
    private String language;
    private int durationHours;
    private String link;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseCourseCategory> courseCategories;


    @Nullable
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentCourse> enrolledStudents;

    @ManyToOne
    private Teacher teacher;

    @Nullable
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseModule> modules;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", language='" + language + '\'' +
                ", durationHours=" + durationHours +
                ", link='" + link + '\'' +
                ", courseCategories=" + (courseCategories != null ? courseCategories.toString() : "[]") +
                ", enrolledStudents=" + (enrolledStudents != null ? enrolledStudents.toString() : "[]") +
                ", teacher=" + (teacher != null ? teacher.toString() : "null") +
                ", modules=" + (modules != null ? modules.toString() : "[]") +
                '}';
    }
}
