package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import unifor.devweb.project.freelearn.domain.entities.CourseCourseCategory;
import unifor.devweb.project.freelearn.dto.CourseCourseCategoryDTO;

@Mapper(componentModel = "spring", uses = {CourseMapper.class, CourseCategoryMapper.class})
public interface CourseCourseCategoryMapper {

    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "category.id", target = "categoryId")
    CourseCourseCategoryDTO toDTO(CourseCourseCategory courseCourseCategory);

    @InheritInverseConfiguration
    CourseCourseCategory toEntity(CourseCourseCategoryDTO courseCourseCategoryDTO);
}


