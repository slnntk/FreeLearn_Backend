package unifor.devweb.project.freelearn.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import unifor.devweb.project.freelearn.domain.entities.*;
import unifor.devweb.project.freelearn.domain.requests.course.CourseGetRequest;
import unifor.devweb.project.freelearn.domain.requests.course.CoursePostRequest;
import unifor.devweb.project.freelearn.domain.requests.course.CoursePutRequest;
import unifor.devweb.project.freelearn.domain.requests.course.CourseRequest;
import unifor.devweb.project.freelearn.repository.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Log4j2
@RequiredArgsConstructor
public class CourseMapperImpl {

    private final CourseCategoryRepository courseCategoryRepository;
    private final CourseModuleRepository courseModuleRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final StudentCourseRepository studentCourseRepository;

    public Course toCourse(CoursePostRequest coursePostRequest) {
        log.info(coursePostRequest);
        if (coursePostRequest == null) {
            return null;
        }

        Course course = new Course();
        mapCourseFields(course, coursePostRequest);
        mapCourseCategories(course, coursePostRequest.getCourseCategoryIds());
        enrollStudents(course, coursePostRequest.getEnrolledStudentIds());

        return course;
    }

    public Course toCourse(Course existingCourse, CoursePutRequest coursePutRequest) {
        log.info(coursePutRequest);
        if (existingCourse == null || coursePutRequest == null) {
            return null;
        }

        mapCourseFields(existingCourse, coursePutRequest);
        mapCourseCategories(existingCourse, coursePutRequest.getCourseCategoryIds());
        mapCourseModules(existingCourse, coursePutRequest.getModuleIds());
        enrollStudents(existingCourse, coursePutRequest.getEnrolledStudentIds());

        return existingCourse;
    }

    public CourseGetRequest fromCourseToGetRequest(Course course) {
        if (course == null) {
            return null;
        }

        CourseGetRequest courseGetRequest = new CourseGetRequest();
        courseGetRequest.setId(course.getId());
        courseGetRequest.setTitle(course.getTitle());
        courseGetRequest.setDescription(course.getDescription());
        courseGetRequest.setImageUrl(course.getImageUrl());
        courseGetRequest.setLanguage(course.getLanguage());
        courseGetRequest.setDurationHours(course.getDurationHours());
        courseGetRequest.setLink(course.getLink());
        courseGetRequest.setTeacherId(course.getTeacher().getId());
        courseGetRequest.setCourseCategoryIds(course.getCourseCategories().stream()
                .map(courseCategory -> courseCategory.getId())
                .collect(Collectors.toList()));
        courseGetRequest.setModuleIds(course.getModules().stream()
                .map(courseModule -> courseModule.getId())
                .collect(Collectors.toList()));

        return courseGetRequest;
    }

    private void mapCourseFields(Course course, CourseRequest courseRequest) {
        course.setTeacher(coursePostRequestToTeacher(courseRequest));
        course.setTitle(courseRequest.getTitle());
        course.setDescription(courseRequest.getDescription());
        course.setImageUrl(courseRequest.getImageUrl());
        course.setLanguage(courseRequest.getLanguage());
        course.setDurationHours(courseRequest.getDurationHours());
        course.setLink(courseRequest.getLink());
    }

    private Teacher coursePostRequestToTeacher(CourseRequest courseRequest) {
        if (courseRequest == null) {
            return null;
        }
        return teacherRepository.findById(courseRequest.getTeacherId()).orElse(null);
    }

    private void mapCourseCategories(Course course, List<Long> courseCategoryIds) {
        if (courseCategoryIds != null) {
            List<CourseCategory> categories = courseCategoryIds.stream()
                    .map(id -> courseCategoryRepository.findById(id).orElse(null))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            course.setCourseCategories(categories);
        }
    }

    public void mapCourseModules(Course course, List<Long> moduleIds) {
        if (moduleIds != null) {
            List<CourseModule> modules = moduleIds.stream()
                    .map(id -> {
                        CourseModule module = courseModuleRepository.findById(id).orElse(null);
                        if (module != null) {
                            if (module.getCourse() == null) {
                                module.setCourse(course);
                                courseModuleRepository.save(module);
                            } else {
                                module.setCourse(course);
                            }
                        }
                        return module;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            course.setModules(modules);
        }
    }

    public List<CourseModule> mapCourseModulesReturnsList(Course course, List<Long> moduleIds) {
        if (moduleIds != null) {
            List<CourseModule> modules = moduleIds.stream()
                    .map(id -> {
                        CourseModule module = courseModuleRepository.findById(id).orElse(null);
                        if (module != null) {
                            if (module.getCourse() == null) {
                                module.setCourse(course);
                                courseModuleRepository.save(module);
                            } else {
                                module.setCourse(course);
                            }
                        }
                        return module;
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            course.setModules(modules);
            return modules;
        }
        return Collections.emptyList();
    }


    private void enrollStudents(Course course, List<Long> studentIds) {
        if (studentIds != null) {
            for (Long studentId : studentIds) {
                Student student = studentRepository.findById(studentId).orElse(null);
                if (student != null) {
                    StudentCourse studentCourse = new StudentCourse();
                    studentCourse.setCourse(course);
                    log.info(studentCourse);
                    studentCourse.setStudent(student);
                    log.info(studentCourse);
                    studentCourseRepository.save(studentCourse);
                }
            }
        }
    }

}
