package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.dto.CourseDTO;

@Mapper(componentModel = "spring", uses = {CourseModuleMapper.class, CourseCourseCategoryMapper.class,  StudentCourseMapper.class})
public interface CourseMapper {

    @Mapping(source = "courseCategories", target = "courseCategoryDTOS")
    @Mapping(source = "enrolledStudents", target = "studentCourseDTOS")
    @Mapping(source = "teacher", target = "teacherDTO")
    @Mapping(source = "modules", target = "moduleDTOList")
    CourseDTO toDTO(Course course);

    @InheritInverseConfiguration
    Course toEntity(CourseDTO courseDTO);
}

