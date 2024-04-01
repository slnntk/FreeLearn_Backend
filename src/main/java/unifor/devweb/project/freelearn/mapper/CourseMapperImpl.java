package unifor.devweb.project.freelearn.mapper;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import unifor.devweb.project.freelearn.domain.entities.*;
import unifor.devweb.project.freelearn.domain.requests.course.CoursePostRequest;
import unifor.devweb.project.freelearn.repository.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Log4j2
public class CourseMapperImpl {

    private final CourseCategoryRepository courseCategoryRepository;
    private final CourseModuleRepository courseModuleRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;

    public CourseMapperImpl(CourseCategoryRepository courseCategoryRepository, CourseModuleRepository courseModuleRepository, TeacherRepository teacherRepository, StudentRepository studentRepository, StudentCourseRepository studentCourseRepository) {
        this.courseCategoryRepository = courseCategoryRepository;
        this.courseModuleRepository = courseModuleRepository;
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
        this.studentCourseRepository = studentCourseRepository;
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

        // Mapeando categorias do curso
        if (coursePostRequest.getCourseCategoryIds() != null) {
            List<CourseCategory> categories = coursePostRequest.getCourseCategoryIds().stream()
                    .map(id -> courseCategoryRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            course.setCourseCategories(categories);
        }

        // Mapeando módulos do curso
        if (coursePostRequest.getModuleIds() != null) {
            List<CourseModule> modules = coursePostRequest.getModuleIds().stream()
                    .map(id -> courseModuleRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            course.setModules(modules);
        }

        // Mapeando alunos matriculados no curso
        enrollStudents(course, coursePostRequest.getEnrolledStudentIds());

        return course;
    }


    protected Teacher coursePostRequestToTeacher(CoursePostRequest coursePostRequest) {
        if (coursePostRequest == null) {
            return null;
        }

        return teacherRepository.findById(coursePostRequest.getTeacherId()).orElse(null);
    }

    public void enrollStudents(Course course, List<Long> studentIds) {
        if (studentIds != null) {
            for (Long studentId : studentIds) {
                Student student = studentRepository.findById(studentId).orElse(null);
                if (student != null) {
                    StudentCourse studentCourse = new StudentCourse();
                    studentCourse.setCourse(course);
                    studentCourse.setStudent(student);
                    studentCourseRepository.save(studentCourse);
                }
            }
        }
    }

}
