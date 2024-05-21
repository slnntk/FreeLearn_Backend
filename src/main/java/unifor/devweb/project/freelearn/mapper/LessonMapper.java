package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.*;
import unifor.devweb.project.freelearn.domain.entities.Lesson;
import unifor.devweb.project.freelearn.dto.LessonDTO;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(source = "module", target = "courseModule")
    LessonDTO toDTO(Lesson lesson);

    @InheritInverseConfiguration
    Lesson toEntity(LessonDTO lessonDTO);
}

