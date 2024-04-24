package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.Lesson;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.dto.LessonDTO;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    LessonMapper INSTANCE = Mappers.getMapper(LessonMapper.class);

    @Mapping(source = "module.id", target = "moduleId")
    LessonDTO toDTO(Lesson lesson);

    @Mapping(source = "moduleId", target = "module", qualifiedByName = "mapModuleIdToCourseModule")
    Lesson toEntity(LessonDTO lessonDTO, @Context CycleAvoidingMappingContext context);

    @Named("mapModuleIdToCourseModule")
    default CourseModule mapModuleIdToCourseModule(Long moduleId, @Context CycleAvoidingMappingContext context) {
        if (moduleId == null) {
            return null;
        }
        return context.getCourseModuleService().findByIdOrThrowBadRequestException(moduleId);
    }
}
