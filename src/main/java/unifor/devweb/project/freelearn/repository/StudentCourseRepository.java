package unifor.devweb.project.freelearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.Student;
import unifor.devweb.project.freelearn.domain.entities.StudentCourse;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse, Long> {
    boolean existsByStudentAndCourse(Student student, Course course);

}
