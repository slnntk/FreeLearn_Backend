package unifor.devweb.project.freelearn.domain.entities;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import unifor.devweb.project.freelearn.serialization.CustomCourseCategorySerialization;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(using = CustomCourseCategorySerialization.class)
public class CourseCategory {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Nullable
    @OneToMany(mappedBy = "category")
    private List<CourseCourseCategory> courses;

    @Override
    public String toString() {
        return "CourseCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", courses='" + courses + '\'' +
                '}';
    }

}
