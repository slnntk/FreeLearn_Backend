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
public class CourseModule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;
    private int sequenceNumber;

    @ManyToOne
    @JsonIgnore
    private Course course;

    @OneToMany(mappedBy = "module")
    private List<Lesson> lessons;

}
