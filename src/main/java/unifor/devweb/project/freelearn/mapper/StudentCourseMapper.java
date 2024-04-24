package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import unifor.devweb.project.freelearn.domain.entities.StudentCourse;
import unifor.devweb.project.freelearn.dto.StudentCourseDTO;

@Mapper(componentModel = "spring")
public interface StudentCourseMapper {
    StudentCourseMapper INSTANCE = Mappers.getMapper(StudentCourseMapper.class);

    StudentCourseDTO toDTO(StudentCourse studentCourse);

    StudentCourse toEntity(StudentCourseDTO studentCourseDTO);
}

