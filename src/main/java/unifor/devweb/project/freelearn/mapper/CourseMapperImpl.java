package unifor.devweb.project.freelearn.mapper;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.CourseCategory;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.entities.Student;
import unifor.devweb.project.freelearn.domain.entities.Teacher;
import unifor.devweb.project.freelearn.domain.requests.course.CoursePostRequest;
import unifor.devweb.project.freelearn.repository.CourseCategoryRepository;
import unifor.devweb.project.freelearn.repository.CourseModuleRepository;
import unifor.devweb.project.freelearn.repository.StudentRepository;
import unifor.devweb.project.freelearn.repository.TeacherRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Log4j2
public class CourseMapperImpl {

    private final CourseCategoryRepository courseCategoryRepository;
    private final StudentRepository studentRepository;
    private final CourseModuleRepository courseModuleRepository;
    private final TeacherRepository teacherRepository;

    public CourseMapperImpl(CourseCategoryRepository courseCategoryRepository,
                            StudentRepository studentRepository,
                            CourseModuleRepository courseModuleRepository,
                            TeacherRepository teacherRepository) {
        this.courseCategoryRepository = courseCategoryRepository;
        this.studentRepository = studentRepository;
        this.courseModuleRepository = courseModuleRepository;
        this.teacherRepository = teacherRepository;
    }

    public Course toCourse(CoursePostRequest coursePostRequest) {
        log.info(coursePostRequest);
        if (coursePostRequest == null) {
            return null;
        }

        Course course = new Course();

        course.setTeacher(coursePostRequestToTeacher(coursePostRequest));
        course.setTitle(coursePostRequest.getTitle());
        course.setDescription(coursePostRequest.getDescription());
        course.setImageUrl(coursePostRequest.getImageUrl());
        course.setLanguage(coursePostRequest.getLanguage());
        course.setDurationHours(coursePostRequest.getDurationHours());
        course.setLink(coursePostRequest.getLink());

        if (coursePostRequest.getCourseCategoryIds() != null) {
            List<Long> categoryIds = coursePostRequest.getCourseCategoryIds();
            List<CourseCategory> categories = categoryIds.stream()
                    .map(id -> mapIdToEntity(id, courseCategoryRepository, "Category"))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            course.setCourseCategories(categories);
        }

        if (coursePostRequest.getEnrolledStudentIds() != null) {
            List<Long> studentIds = coursePostRequest.getEnrolledStudentIds();
            List<Student> students = studentIds.stream()
                    .map(id -> mapIdToEntity(id, studentRepository, "Student"))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            course.setEnrolledStudents(students);
        }

        if (coursePostRequest.getModuleIds() != null) {
            List<Long> moduleIds = coursePostRequest.getModuleIds();
            List<CourseModule> modules = moduleIds.stream()
                    .map(id -> mapIdToEntity(id, courseModuleRepository, "Module"))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            course.setModules(modules);
            System.out.println(modules);
        }


        return course;
    }

    protected Teacher coursePostRequestToTeacher(CoursePostRequest coursePostRequest) {
        if (coursePostRequest == null) {
            return null;
        }

        Teacher teacher = null;
        if (coursePostRequest.getTeacherId() != null) {
            teacher = teacherRepository.findById(coursePostRequest.getTeacherId()).orElse(null);
        }

        return teacher;
    }

    private <T> T mapIdToEntity(Long id, JpaRepository<T, Long> repository, String entityName) {
        T entity = repository.findById(id).orElse(null);
        if (entity == null) {
            log.warn("{} with ID {} not found", entityName, id);
        } else {
            log.info("{} with ID {} found", entityName, id);
        }
        return entity;
    }
}
