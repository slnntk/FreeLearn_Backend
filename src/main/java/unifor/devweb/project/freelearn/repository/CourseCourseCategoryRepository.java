package unifor.devweb.project.freelearn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.CourseCategory;
import unifor.devweb.project.freelearn.domain.entities.CourseCourseCategory;

@Repository
public interface CourseCourseCategoryRepository extends JpaRepository<CourseCourseCategory, Long> {
    boolean existsByCourseAndCategory(Course course, CourseCategory category);
}
