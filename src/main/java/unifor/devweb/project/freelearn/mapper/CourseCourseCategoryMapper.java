package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import unifor.devweb.project.freelearn.domain.entities.CourseCourseCategory;
import unifor.devweb.project.freelearn.dto.CourseCourseCategoryDTO;

@Mapper(componentModel = "spring")
public interface CourseCourseCategoryMapper {
    CourseCourseCategoryMapper INSTANCE = Mappers.getMapper(CourseCourseCategoryMapper.class);

    CourseCourseCategoryDTO toDTO(CourseCourseCategory courseCourseCategory);

    CourseCourseCategory toEntity(CourseCourseCategoryDTO courseCourseCategoryDTO);
}
