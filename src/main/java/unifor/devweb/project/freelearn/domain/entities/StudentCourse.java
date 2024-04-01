package unifor.devweb.project.freelearn.domain.entities;

import jakarta.persistence.*;
import lombok.Data;

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
}
