package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.*;
import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Lazy;
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.Lesson;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.dto.LessonDTO;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(source = "module", target = "courseModule")
    LessonDTO toDTO(Lesson lesson);

    @InheritInverseConfiguration
    Lesson toEntity(LessonDTO lessonDTO);
}

