package unifor.devweb.project.freelearn.domain.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String comment;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Teacher teacher;

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                ", student=" + (student != null ? "Student{id=" + student.getId() + "}" : "null") +
                ", course=" + (course != null ? "Course{id=" + course.getId() + "}" : "null") +
                ", teacher=" + (teacher != null ? "Teacher{id=" + teacher.getId() + "}" : "null") +
                '}';
    }
}
