package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Lazy;
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.Review;
import unifor.devweb.project.freelearn.domain.entities.Student;
import unifor.devweb.project.freelearn.domain.entities.StudentCourse;
import unifor.devweb.project.freelearn.dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class, StudentCourseMapper.class, ReviewMapper.class})
public interface StudentMapper {

    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "enrolledCourses", target = "studentCourseDTOList")
    @Mapping(source = "reviews", target = "reviewDTOList")
    StudentDTO toDTO(Student student);

    @InheritInverseConfiguration
    Student toEntity(StudentDTO studentDTO);
}


