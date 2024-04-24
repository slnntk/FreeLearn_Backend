package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.entities.Lesson;
import unifor.devweb.project.freelearn.dto.CourseModuleDTO;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseModuleMapper {

    LessonMapper lessonMapper = Mappers.getMapper(LessonMapper.class);

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "lessons", target = "lessonIds", qualifiedByName = "mapLessonsToIds")
    CourseModuleDTO toDTO(CourseModule courseModule);

    @Named("mapLessonsToIds")
    default List<Long> mapLessonsToIds(List<Lesson> lessons) {
        if (lessons == null || lessons.isEmpty()) {
            return null;
        }
        return lessons.stream()
                .map(Lesson::getId)
                .toList();
    }

    @Mapping(target = "course.id", source = "courseId")
    @Mapping(target = "lessons", source = "lessonIds", qualifiedByName = "mapIdsToLessons")
    CourseModule toEntity(CourseModuleDTO courseModuleDTO, @Context CycleAvoidingMappingContext context);

    @Named("mapIdsToLessons")
    default List<Lesson> mapIdsToLessons(List<Long> lessonIds, @Context CycleAvoidingMappingContext context) {
        if (lessonIds == null || lessonIds.isEmpty()) {
            return null;
        }
        return lessonIds.stream()
                .map(context.getLessonService()::findByIdOrThrowBadRequestException)
                .toList();
    }
}
