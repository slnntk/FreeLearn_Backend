package unifor.devweb.project.freelearn.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.entities.Lesson;
import unifor.devweb.project.freelearn.domain.requests.lesson.LessonGetRequest;
import unifor.devweb.project.freelearn.domain.requests.lesson.LessonPostRequest;
import unifor.devweb.project.freelearn.domain.requests.lesson.LessonPutRequest;
import unifor.devweb.project.freelearn.domain.requests.lesson.LessonRequest;
import unifor.devweb.project.freelearn.repository.CourseModuleRepository;

import java.util.Objects;

@Component
@Log4j2
@RequiredArgsConstructor
public class LessonMapperImpl {

    private final CourseModuleRepository courseModuleRepository;

    public Lesson toLesson(LessonPostRequest lessonPostRequest) {
        log.info(lessonPostRequest);
        if (lessonPostRequest == null) {
            return null;
        }

        Lesson lesson = new Lesson();
        mapLessonFields(lesson, lessonPostRequest);

        return lesson;
    }

    public Lesson toLesson(Lesson existingLesson, LessonPutRequest lessonPutRequest) {
        log.info(lessonPutRequest);
        if (existingLesson == null || lessonPutRequest == null) {
            return null;
        }

        mapLessonFields(existingLesson, lessonPutRequest);

        return existingLesson;
    }

    public LessonGetRequest fromLessonToGetRequest(Lesson lesson) {
        if (lesson == null) {
            return null;
        }

        LessonGetRequest lessonGetRequest = new LessonGetRequest();
        lessonGetRequest.setId(lesson.getId());
        lessonGetRequest.setTitle(lesson.getTitle());
        lessonGetRequest.setVideoUrl(lesson.getVideoUrl());
        lessonGetRequest.setDurationMinutes(lesson.getDurationMinutes());
        if (lesson.getModule() != null) {
            lessonGetRequest.setModuleId(lesson.getModule().getId());
        }
        if (Objects.requireNonNull(lesson.getModule()).getCourse() != null){
            lessonGetRequest.setCourseId((lesson.getModule().getCourse().getId()));
        }

        return lessonGetRequest;
    }

    private void mapLessonFields(Lesson lesson, LessonRequest lessonRequest) {
        lesson.setTitle(lessonRequest.getTitle());
        lesson.setVideoUrl(lessonRequest.getVideoUrl());
        lesson.setDurationMinutes(lessonRequest.getDurationMinutes());
        mapLessonModule(lesson, lessonRequest.getModuleId());
    }

    private void mapLessonModule(Lesson lesson, Long moduleId) {
        if (moduleId != null) {
            CourseModule module = courseModuleRepository.findById(moduleId).orElse(null);
            if (module != null) {
                lesson.setModule(module);
            }
        }
    }
}
