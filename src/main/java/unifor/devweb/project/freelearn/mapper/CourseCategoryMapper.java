package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import unifor.devweb.project.freelearn.domain.entities.CourseCategory;
import unifor.devweb.project.freelearn.dto.CourseCategoryDTO;

@Mapper(componentModel = "spring", uses = {CourseModuleMapper.class, CourseCourseCategoryMapper.class})
public interface CourseCategoryMapper {

    @Mapping(source = "courses", target = "courseCategory")
    CourseCategoryDTO toDTO(CourseCategory courseCategory);

    @InheritInverseConfiguration
    CourseCategory toEntity(CourseCategoryDTO courseCategoryDTO);
}




