package unifor.devweb.project.freelearn.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table(name = "course_course_category")
public class CourseCourseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CourseCategory category;

    @Override
    public String toString() {
        return "CourseCourseCategory{" +
                "id=" + id +
                ", course=" + (course != null ? course.getId() : "null") +
                ", category=" + (category != null ? category.getId() : "null") +
                '}';
    }
}
