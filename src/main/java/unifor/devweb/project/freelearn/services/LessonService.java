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

    private final CourseModuleRepository courseModuleRepository;
    private final LessonRepository lessonRepository;

    public Page<Lesson> listAll(Long courseId, Pageable pageable) {
        return lessonRepository.findByModuleIdPageable(courseId, pageable);
    }

    public Iterable<Lesson> listAllNonPageable(Long courseId) {
        return lessonRepository.findByModuleId(courseId);
    }

    public Lesson findByIdOrThrowBadRequestException(Long lessonId) {
        return lessonRepository.findById(lessonId)
                .orElseThrow(() -> new ObjectNotFoundException("LessonDTO not found"));
    }

    @Transactional
    public Lesson save(Long moduleId, Lesson lesson) {
        CourseModule courseModule = courseModuleRepository.findById(moduleId)
                .orElseThrow(() -> new ObjectNotFoundException("Course Module not found"));
        lesson.setModule(courseModule);
        return lessonRepository.save(lesson);
    }

    public void replace(Lesson updatedLesson) {
        Lesson existingLesson = findByIdOrThrowBadRequestException(updatedLesson.getId());
        replaceData(updatedLesson, existingLesson);
        lessonRepository.save(existingLesson);
    }

    private void replaceData(Lesson updatedLesson, Lesson existingLesson) {
        existingLesson.setTitle(updatedLesson.getTitle());
        existingLesson.setDurationMinutes(updatedLesson.getDurationMinutes());
        existingLesson.setVideoUrl(updatedLesson.getVideoUrl());
    }


    public void delete(Long lessonId) {
        Lesson lesson = findByIdOrThrowBadRequestException(lessonId);
        lessonRepository.delete(lesson);
    }
}
