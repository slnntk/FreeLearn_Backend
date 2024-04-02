package unifor.devweb.project.freelearn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.requests.course.CourseGetRequest;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

}