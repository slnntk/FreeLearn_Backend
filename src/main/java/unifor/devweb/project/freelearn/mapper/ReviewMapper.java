package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.Review;
import unifor.devweb.project.freelearn.domain.entities.Student;
import unifor.devweb.project.freelearn.domain.entities.Teacher;
import unifor.devweb.project.freelearn.dto.ReviewDTO;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "teacher.id", target = "teacherId")
    ReviewDTO toDTO(Review review);

    @Mapping(source = "studentId", target = "student", qualifiedByName = "mapStudentIdToStudent")
    @Mapping(source = "courseId", target = "course", qualifiedByName = "mapCourseIdToCourse")
    @Mapping(source = "teacherId", target = "teacher", qualifiedByName = "mapTeacherIdToTeacher")
    Review toEntity(ReviewDTO reviewDTO, @Context CycleAvoidingMappingContext context);

    @Named("mapStudentIdToStudent")
    default Student mapStudentIdToStudent(Long studentId, @Context CycleAvoidingMappingContext context) {
        if (studentId == null) {
            return null;
        }
        return context.getStudentService().findByIdOrThrowBadRequestException(studentId);
    }

    @Named("mapCourseIdToCourse")
    default Course mapCourseIdToCourse(Long courseId, @Context CycleAvoidingMappingContext context) {
        if (courseId == null) {
            return null;
        }
        return context.getCourseService().findByIdOrThrowBadRequestException(courseId);
    }

    @Named("mapTeacherIdToTeacher")
    default Teacher mapTeacherIdToTeacher(Long teacherId, @Context CycleAvoidingMappingContext context) {
        if (teacherId == null) {
            return null;
        }
        return context.getTeacherService().findByIdOrThrowBadRequestException(teacherId);
    }
}
