package unifor.devweb.project.freelearn.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.entities.Lesson;
import unifor.devweb.project.freelearn.domain.requests.coursemodule.CourseModulePostRequest;
import unifor.devweb.project.freelearn.domain.requests.coursemodule.CourseModulePutRequest;
import unifor.devweb.project.freelearn.domain.requests.coursemodule.CourseModuleRequest;
import unifor.devweb.project.freelearn.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
public class CourseModuleMapperImpl {

    private final CourseRepository courseRepository;

    public CourseModule toCourseModule(CourseModulePostRequest courseModulePostRequest) {
        log.info(courseModulePostRequest);
        if (courseModulePostRequest == null) {
            return null;
        }

        CourseModule courseModule = new CourseModule();
        mapCourseModuleFields(courseModule, courseModulePostRequest);

        return courseModule;
    }

    public Course toCourseModule(CourseModule existingCourseModule, CourseModulePutRequest courseModulePutRequest) {
        log.info(courseModulePutRequest);
        if (existingCourseModule == null || existingCourseModule == null) {
            return null;
        }

        mapCourseModuleFields(existingCourseModule, courseModulePutRequest);


        return existingCourseModule;
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
