package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import unifor.devweb.project.freelearn.domain.entities.StudentCourse;
import unifor.devweb.project.freelearn.dto.StudentCourseDTO;

@Mapper(componentModel = "spring", uses = {StudentMapper.class, CourseMapper.class})
public interface StudentCourseMapper {

    @Mapping(source = "student.id", target = "studentId")
    @Mapping(source = "course.id", target = "courseId")
    StudentCourseDTO toDTO(StudentCourse studentCourse);

    @InheritInverseConfiguration
    StudentCourse toEntity(StudentCourseDTO studentCourseDTO);
}



