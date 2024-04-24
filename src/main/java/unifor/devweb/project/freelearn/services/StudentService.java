package unifor.devweb.project.freelearn.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import unifor.devweb.project.freelearn.domain.entities.Student;
import unifor.devweb.project.freelearn.domain.entities.StudentCourse;
import unifor.devweb.project.freelearn.exception.ObjectNotFoundException;
import unifor.devweb.project.freelearn.repository.StudentCourseRepository;
import unifor.devweb.project.freelearn.repository.StudentRepository;

@Service
@RequiredArgsConstructor
public class StudentService {
    
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;

    public Page<Student> listAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    public Iterable<Student> listAllNonPageable() {
        return studentRepository.findAll();
    }

    public Student findByIdOrThrowBadRequestException(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Student not Found"));
    }

    public StudentCourse findStudentCourseById(long id) {
        return studentCourseRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Student not Found"));
    }

    @Transactional
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Transactional
    public void replace(Student updatedStudent) {
        Student existingStudent = findByIdOrThrowBadRequestException(updatedStudent.getId());
        replaceData(updatedStudent, existingStudent);
        studentRepository.save(existingStudent);
    }

    private void replaceData(Student updatedStudent, Student existingStudent) {

    }

    public void delete(Long id) {
        studentRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}
