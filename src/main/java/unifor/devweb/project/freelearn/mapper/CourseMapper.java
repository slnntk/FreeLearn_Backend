package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.requests.Course.CoursePostRequestBody;
import unifor.devweb.project.freelearn.requests.Course.CoursePutRequestBody;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "courseCategories", ignore = true)
    @Mapping(target = "enrolledStudents", ignore = true)
    @Mapping(target = "modules", ignore = true)
    Course toCourse(CoursePostRequestBody request);

    @Mapping(target = "courseCategories", ignore = true)
    @Mapping(target = "enrolledStudents", ignore = true)
    @Mapping(target = "modules", ignore = true)
    Course toCourse(CoursePutRequestBody request);
}
