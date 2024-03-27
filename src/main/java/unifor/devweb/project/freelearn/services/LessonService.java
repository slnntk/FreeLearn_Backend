package unifor.devweb.project.freelearn.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.entities.Lesson;
import unifor.devweb.project.freelearn.exception.ObjectNotFoundException;
import unifor.devweb.project.freelearn.repository.CourseModuleRepository;
import unifor.devweb.project.freelearn.repository.LessonRepository;

@Service
@RequiredArgsConstructor
public class LessonService {

    private final LessonRepository lessonRepository;
    private final CourseModuleRepository courseModuleRepository;


    public Page<Lesson> listAll(Long moduleId, Pageable pageable) {
        return lessonRepository.findByModuleId(moduleId, pageable);
    }

    public Iterable<Lesson> listAllNonPageable(Long moduleId) {
        return lessonRepository.findByModuleId(moduleId);
    }

    @Transactional
    public Lesson save(Long moduleId, Lesson lesson) {
        CourseModule module = courseModuleRepository.findById(moduleId)
                .orElseThrow(() -> new ObjectNotFoundException("Course module not found"));

        lesson.setModule(module);
        return lessonRepository.save(lesson);
    }

    public Lesson findByIdOrThrowBadRequestException(Long id) {
        return lessonRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Lesson not found"));
    }

    @Transactional
    public Lesson replace(Long id, Lesson updatedLesson) {
        Lesson existingLesson = findByIdOrThrowBadRequestException(id);
        updatedLesson.setId(existingLesson.getId());
        updatedLesson.setModule(existingLesson.getModule());
        return lessonRepository.save(updatedLesson);
    }

    public void delete(Long id) {
        Lesson existingLesson = findByIdOrThrowBadRequestException(id);
        lessonRepository.delete(existingLesson);
    }
}
