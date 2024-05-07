package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Lazy;
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.Review;
import unifor.devweb.project.freelearn.domain.entities.Student;
import unifor.devweb.project.freelearn.domain.entities.Teacher;
import unifor.devweb.project.freelearn.dto.ReviewDTO;

@Mapper(componentModel = "spring", uses = {StudentCourseMapper.class, CourseMapper.class, TeacherMapper.class})
public interface ReviewMapper {

    @Mapping(source = "student", target = "student")
    @Mapping(source = "course", target = "course")
    @Mapping(source = "teacher", target = "teacher")
    ReviewDTO toDTO(Review review);

    @InheritInverseConfiguration
    Review toEntity(ReviewDTO reviewDTO);
}

