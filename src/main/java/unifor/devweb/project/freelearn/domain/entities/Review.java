package unifor.devweb.project.freelearn.domain.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import unifor.devweb.project.freelearn.serialization.CustomReviewSerialization;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = CustomReviewSerialization.class)
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
