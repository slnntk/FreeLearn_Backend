package unifor.devweb.project.freelearn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unifor.devweb.project.freelearn.domain.entities.Lesson;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    @Query("SELECT l FROM Lesson l WHERE l.module.id = :moduleId")
    Page<Lesson> findByModuleId(Long moduleId, Pageable pageable);

    @Query("SELECT l FROM Lesson l WHERE l.module.id = :moduleId")
    Iterable<Lesson> findByModuleId(Long moduleId);
}