package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.*;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.dto.CourseModuleDTO;

@Mapper(componentModel = "spring", uses = {LessonMapper.class})
public interface CourseModuleMapper {

    @Mapping(source = "course", target = "course")
    @Mapping(source = "lessons", target = "lessonDTOS")
    CourseModuleDTO toDTO(CourseModule courseModule);

    @InheritInverseConfiguration
    CourseModule toEntity(CourseModuleDTO courseModuleDTO);
}
