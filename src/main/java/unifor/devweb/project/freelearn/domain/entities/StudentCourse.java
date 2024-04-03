package unifor.devweb.project.freelearn.domain.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "student_course")
@Data
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Override
    public String toString() {
        return "StudentCourse{" +
                "id=" + id +
                ", course=" + (course != null ? course.getId() : "null") +
                ", student=" + (student != null ? student.getId() : "null") +
                '}';
    }

}
