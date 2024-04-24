package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.Teacher;
import unifor.devweb.project.freelearn.dto.TeacherDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "courses", target = "courseIds", qualifiedByName = "mapCoursesToDTO")
    TeacherDTO toDTO(Teacher teacher);

    @Named("mapCoursesToDTO")
    default List<Long> mapCoursesToDTO(List<Course> courses) {
        if (courses == null || courses.isEmpty()) {
            return new ArrayList<>();
        }
        return courses.stream()
                .map(Course::getId)
                .toList();
    }

    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "courses", source = "courseIds", qualifiedByName = "mapDTOsToCourses")
    Teacher toEntity(TeacherDTO teacherDTO, @Context CycleAvoidingMappingContext context);

    @Named("mapDTOsToCourses")
    default List<Course> mapDTOsToCourses(List<Long> courseIds, @Context CycleAvoidingMappingContext context) {
        if (courseIds == null || courseIds.isEmpty()) {
            return null;
        }
        return courseIds.stream()
                .map(courseId -> {
                    Course course = context.getCourseService().findByIdOrThrowBadRequestException(courseId);
                    return course;
                })
                .toList();
    }
}


