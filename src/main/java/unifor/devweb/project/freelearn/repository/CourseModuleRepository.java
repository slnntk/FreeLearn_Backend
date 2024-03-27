package unifor.devweb.project.freelearn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;

import java.util.List;

@Repository
public interface CourseModuleRepository extends JpaRepository<CourseModule, Long> {

    @Query("SELECT cm FROM CourseModule cm WHERE cm.course.id = :courseId")
    Page<CourseModule> findAllByCourseId(Long courseId, Pageable pageable);

    @Query("SELECT cm FROM CourseModule cm WHERE cm.course.id = :courseId")
    Iterable<CourseModule> findAllByCourseId(Long courseId);
}