package unifor.devweb.project.freelearn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import unifor.devweb.project.freelearn.domain.entities.Student;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s WHERE s.id = :studentId")
    Page<Student> findStudentById(Long studentId, Pageable pageable);

    @Query("SELECT s FROM Student s WHERE s.id = :studentId")
    Optional<Student> findStudentById(Long studentId);

}
