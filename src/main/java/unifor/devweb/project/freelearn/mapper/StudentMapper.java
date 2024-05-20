package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.*;
import unifor.devweb.project.freelearn.domain.entities.Student;
import unifor.devweb.project.freelearn.dto.StudentDTO;

@Mapper(componentModel = "spring", uses = {UserMapper.class, StudentCourseMapper.class, ReviewMapper.class})
public interface StudentMapper {

    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "enrolledCourses", target = "studentCourseDTOList")
    @Mapping(source = "reviews", target = "reviewDTOList")
    StudentDTO toDTO(Student student);

    @InheritInverseConfiguration
    Student toEntity(StudentDTO studentDTO);
}


