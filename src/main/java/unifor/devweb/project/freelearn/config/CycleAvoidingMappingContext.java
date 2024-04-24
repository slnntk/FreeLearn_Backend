package unifor.devweb.project.freelearn.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import unifor.devweb.project.freelearn.repository.CourseCourseCategoryRepository;
import unifor.devweb.project.freelearn.repository.StudentCourseRepository;
import unifor.devweb.project.freelearn.services.*;

@Component
@RequiredArgsConstructor
@Getter
public class CycleAvoidingMappingContext {

    private final CourseService courseService;
    private final CourseCategoryService courseCategoryService;
    private final StudentService studentService;
    private final CourseModuleService courseModuleService;
    private final TeacherService teacherService;
    private final LessonService lessonService;
    private final ReviewService reviewService;
}
