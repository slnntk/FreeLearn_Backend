package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Lazy;
import unifor.devweb.project.freelearn.domain.entities.Teacher;
import unifor.devweb.project.freelearn.dto.TeacherDTO;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CourseMapper.class})
public interface TeacherMapper {
    @Mapping(source = "user", target = "userDTO")
    @Mapping(source = "courses", target = "courseDTOList")
    TeacherDTO toDTO(Teacher teacher);

    @InheritInverseConfiguration
    Teacher toEntity(TeacherDTO teacherDTO);
}



