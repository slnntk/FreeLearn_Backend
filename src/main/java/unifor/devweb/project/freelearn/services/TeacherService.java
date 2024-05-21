package unifor.devweb.project.freelearn.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import unifor.devweb.project.freelearn.domain.entities.Teacher;
import unifor.devweb.project.freelearn.exception.ObjectNotFoundException;
import unifor.devweb.project.freelearn.repository.TeacherRepository;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;

    public Page<Teacher> listAll(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    public Iterable<Teacher> listAllNonPageable() {
        return teacherRepository.findAll();
    }

    public Teacher findByIdOrThrowBadRequestException(long id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Teacher not Found"));
    }

    @Transactional
    public Teacher save(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    @Transactional
    public void replace(Teacher updatedTeacher) {
        Teacher existingTeacher = findByIdOrThrowBadRequestException(updatedTeacher.getId());
        replaceData(updatedTeacher, existingTeacher);
        teacherRepository.save(existingTeacher);
    }

    private void replaceData(Teacher updatedTeacher, Teacher existingTeacher) {

    }

    public void delete(long id) {
        teacherRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}
