package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.CourseCategory;
import unifor.devweb.project.freelearn.domain.entities.CourseCourseCategory;
import unifor.devweb.project.freelearn.dto.CourseCategoryDTO;
import unifor.devweb.project.freelearn.dto.CourseDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseCategoryMapper {

    CourseMapper courseMapper = Mappers.getMapper(CourseMapper.class);

    @Mapping(source = "courses", target = "courses", qualifiedByName = "mapCoursesToDTO")
    CourseCategoryDTO toDTO(CourseCategory courseCategory);

    @Named("mapCoursesToDTO")
    default List<CourseDTO> mapCoursesToDTO(List<CourseCourseCategory> courseCourseCategories) {

        if (courseCourseCategories == null || courseCourseCategories.isEmpty()) {
            return new ArrayList<>();
        }

        return courseCourseCategories.stream()
                .map(CourseCourseCategory::getCourse)
                .map(courseMapper::toDTO)
                .toList();
    }

    @Mapping(target = "courses", source = "courses", qualifiedByName = "mapDTOsToCourseCourseCategories")
    CourseCategory toEntity(CourseCategoryDTO courseCategoryDTO, @Context CycleAvoidingMappingContext context);


    @Named("mapDTOsToCourseCourseCategories")
    default List<CourseCourseCategory> mapDTOsToCourseCourseCategories(List<CourseDTO> courses, @Context CycleAvoidingMappingContext context) {
        if (courses == null || courses.isEmpty()) {
            return null;
        }
        return courses.stream()
                .map(courseDTO -> {
                    Course course = context.getCourseService().findByIdOrThrowBadRequestException(courseDTO.getId());
                    CourseCourseCategory courseCourseCategory = new CourseCourseCategory();
                    courseCourseCategory.setCourse(course);
                    return courseCourseCategory;
                })
                .toList();
    }

    default List<CourseCategory> toEntities(List<CourseCategoryDTO> courseCategoryDTOs, @Context CycleAvoidingMappingContext context) {
        return courseCategoryDTOs.stream()
                .map(dto -> this.toEntity(dto, context))
                .toList();
    }
}




