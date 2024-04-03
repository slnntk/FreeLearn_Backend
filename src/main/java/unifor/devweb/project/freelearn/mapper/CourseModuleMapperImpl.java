package unifor.devweb.project.freelearn.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.entities.Lesson;
import unifor.devweb.project.freelearn.domain.requests.coursemodule.CourseModuleGetRequest;
import unifor.devweb.project.freelearn.domain.requests.coursemodule.CourseModulePostRequest;
import unifor.devweb.project.freelearn.domain.requests.coursemodule.CourseModulePutRequest;
import unifor.devweb.project.freelearn.domain.requests.coursemodule.CourseModuleRequest;
import unifor.devweb.project.freelearn.repository.CourseRepository;
import unifor.devweb.project.freelearn.repository.LessonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Log4j2
@RequiredArgsConstructor
public class CourseModuleMapperImpl {

    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    public CourseModule toCourseModule(CourseModulePostRequest courseModulePostRequest) {
        log.info(courseModulePostRequest);
        log.info(courseModulePostRequest.getCourseId());
        if (courseModulePostRequest == null) {
            return null;
        }

        CourseModule courseModule = new CourseModule();
        mapCourseModuleFields(courseModule, courseModulePostRequest);

        return courseModule;
    }

    public CourseModule toCourseModule(CourseModule existingCourseModule, CourseModulePutRequest courseModulePutRequest) {
        log.info(courseModulePutRequest);
        if (existingCourseModule == null || courseModulePutRequest == null) {
            return null;
        }

        mapCourseModuleFields(existingCourseModule, courseModulePutRequest);
        mapCourseModuleLessons(existingCourseModule, courseModulePutRequest.getLessonsId());

        return existingCourseModule;
    }

    public CourseModuleGetRequest fromCourseModuleToGetRequest(CourseModule courseModule) {
        if (courseModule == null) {
            return null;
        }

        CourseModuleGetRequest request = new CourseModuleGetRequest();
        request.setId(courseModule.getId());
        request.setTitle(courseModule.getTitle());
        request.setDescription(courseModule.getDescription());
        request.setSequenceNumber(courseModule.getSequenceNumber());
        request.setCourseId(courseModule.getCourse().getId());
        request.setLessonsId(courseModule.getLessons().stream().map(lesson -> lesson.getId()).collect(Collectors.toList()));

        return request;
    }

    private void mapCourseModuleLessons(CourseModule courseModule, List<Long> lessonsId) {
        if (lessonsId == null || lessonsId.isEmpty()) {
            return;
        }

        List<Lesson> lessons = new ArrayList<>();
        for (Long lessonId : lessonsId) {
            Lesson lesson = lessonRepository.findById(lessonId).orElse(null);
            if (lesson != null) {
                lessons.add(lesson);
            }
        }

        courseModule.setLessons(lessons);
    }

    private void mapCourseModuleFields(CourseModule courseModule, CourseModuleRequest courseModuleRequest) {
        courseModule.setCourse(courseModulePostRequestToCourse(courseModuleRequest));
        courseModule.setTitle(courseModuleRequest.getTitle());
        courseModule.setDescription(courseModuleRequest.getDescription());
        courseModule.setSequenceNumber(courseModuleRequest.getSequenceNumber());
    }

    private Course courseModulePostRequestToCourse(CourseModuleRequest courseModuleRequest) {
        if (courseModuleRequest == null) {
            return null;
        }
        return courseRepository.findById(courseModuleRequest.getCourseId()).orElse(null);
    }
}
