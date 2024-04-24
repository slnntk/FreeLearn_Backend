package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.*;
import unifor.devweb.project.freelearn.dto.CourseDTO;
import unifor.devweb.project.freelearn.services.CourseCategoryService;
import unifor.devweb.project.freelearn.services.CourseModuleService;
import unifor.devweb.project.freelearn.services.StudentService;
import unifor.devweb.project.freelearn.services.TeacherService;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {CourseCategoryService.class, StudentService.class, CourseModuleService.class, TeacherService.class})
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "courseCategoryIds", source = "courseCategories")
    @Mapping(target = "enrolledStudentIds", source = "enrolledStudents")
    @Mapping(target = "teacherId", source = "teacher.id")
    @Mapping(target = "moduleIds", source = "modules")
    CourseDTO toDTO(Course course);

    default List<Long> mapCourseCategoryIds(List<CourseCourseCategory> courseCategories) {
        return courseCategories.stream()
                .map(CourseCourseCategory::getCategory)
                .map(CourseCategory::getId)
                .toList();
    }

    default List<Long> mapEnrolledStudentIds(List<StudentCourse> enrolledStudents) {
        return enrolledStudents.stream()
                .map(StudentCourse::getStudent)
                .map(Student::getId)
                .toList();
    }

    default List<Long> mapModuleIds(List<CourseModule> modules) {
        return modules.stream()
                .map(CourseModule::getId)
                .toList();
    }

    default Long mapTeacherId(Teacher teacher) {
        return teacher != null ? teacher.getId() : null;
    }

    @Mapping(target = "courseCategories", source = "courseCategoryIds", qualifiedByName = "mapCourseCategories")
    @Mapping(target = "enrolledStudents", source = "enrolledStudentIds", qualifiedByName = "mapEnrolledStudents")
    @Mapping(target = "teacher.id", source = "teacherId", qualifiedByName = "mapTeacher")
    @Mapping(target = "modules", source = "moduleIds", qualifiedByName = "mapModules")
    Course toEntity(CourseDTO courseDTO, @Context CycleAvoidingMappingContext context);

    @Named("mapCourseCategories")
    default List<CourseCourseCategory> mapCourseCategories(List<Long> courseCategoryIds, @Context CycleAvoidingMappingContext context) {
        if (courseCategoryIds == null || courseCategoryIds.isEmpty()) {
            System.out.println("a");
            return new ArrayList<>();
        }
        return courseCategoryIds.stream()
                .map(id -> context.getCourseCategoryService().findCourseCourseCategoryById(id))
                .toList();
    }

    @Named("mapEnrolledStudents")
    default List<StudentCourse> mapEnrolledStudents(List<Long> enrolledStudentIds, @Context CycleAvoidingMappingContext context) {
        if (enrolledStudentIds == null || enrolledStudentIds.isEmpty()) {
            return new ArrayList<>();
        }
        return enrolledStudentIds.stream()
                .map(id -> context.getStudentService().findStudentCourseById(id))
                .toList();
    }

    @Named("mapModules")
    default List<CourseModule> mapModules(List<Long> moduleIds, @Context CycleAvoidingMappingContext context) {
        if (moduleIds == null || moduleIds.isEmpty()) {
            return new ArrayList<>();
        }
        return moduleIds.stream()
                .map(id -> context.getCourseModuleService().findByIdOrThrowBadRequestException(id))
                .toList();
    }


    @Named("mapTeacher")
    default Teacher mapTeacher(Long teacherId, @Context CycleAvoidingMappingContext context) {
        return teacherId != null ? context.getTeacherService().findByIdOrThrowBadRequestException(teacherId) : null;
    }
}
