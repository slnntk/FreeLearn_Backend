package unifor.devweb.project.freelearn.domain.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;
import unifor.devweb.project.freelearn.serialization.CustomCourseSerialization;
import unifor.devweb.project.freelearn.serialization.CustomStudentSerialization;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = CustomStudentSerialization.class)
public class Student {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int hoursWatched;
    private int numberOfCoursesSubscribed;

    @OneToOne
    private User user;

    @Nullable
    @OneToMany(mappedBy = "student")
    private List<Review> reviews;

    @Nullable
    @OneToMany(mappedBy = "student")
    private List<StudentCourse> enrolledCourses;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", hoursWatched=" + hoursWatched +
                ", numberOfCoursesSubscribed=" + numberOfCoursesSubscribed +
                '}';
    }
}
