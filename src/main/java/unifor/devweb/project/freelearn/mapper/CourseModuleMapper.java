package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Lazy;
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.entities.Lesson;
import unifor.devweb.project.freelearn.dto.CourseModuleDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {LessonMapper.class})
public interface CourseModuleMapper {

    @Mapping(source = "course", target = "course")
    @Mapping(source = "lessons", target = "lessonDTOS")
    CourseModuleDTO toDTO(CourseModule courseModule);

    @InheritInverseConfiguration
    CourseModule toEntity(CourseModuleDTO courseModuleDTO);
}
