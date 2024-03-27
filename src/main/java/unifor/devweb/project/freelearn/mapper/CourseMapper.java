package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import unifor.devweb.project.freelearn.config.util.ApplicationContextProvider;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.CourseCategory;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.entities.Student;
import unifor.devweb.project.freelearn.domain.entities.Teacher;
import unifor.devweb.project.freelearn.exception.ObjectNotFoundException;
import unifor.devweb.project.freelearn.repository.CourseCategoryRepository;
import unifor.devweb.project.freelearn.repository.CourseModuleRepository;
import unifor.devweb.project.freelearn.repository.CourseRepository;
import unifor.devweb.project.freelearn.repository.StudentRepository;
import unifor.devweb.project.freelearn.repository.TeacherRepository;
import unifor.devweb.project.freelearn.requests.Course.CoursePostRequestBody;
import unifor.devweb.project.freelearn.requests.Course.CoursePutRequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    CourseMapper INSTANCE = Mappers.getMapper(CourseMapper.class);

    @Mapping(target = "id", ignore = true)
    Course toCourse(CoursePostRequestBody request);

    @Mapping(target = "id", ignore = true)
    Course toCourse(CoursePutRequestBody request);

    @Mapping(target = "teacher", source = "teacherId", qualifiedByName = "mapTeacherId")
    @Mapping(target = "modules", source = "moduleIds", qualifiedByName = "mapModuleIds")
    @Mapping(target = "courseCategories", source = "courseCategoryIds", qualifiedByName = "mapCourseCategoryIds")
    Course toCourseWithDetails(CoursePostRequestBody request);

    @Mapping(target = "teacher", source = "teacherId", qualifiedByName = "mapTeacherId")
    @Mapping(target = "modules", source = "moduleIds", qualifiedByName = "mapModuleIds")
    @Mapping(target = "courseCategories", source = "courseCategoryIds", qualifiedByName = "mapCourseCategoryIds")
    Course toCourseWithDetails(CoursePutRequestBody request);

    @Named("mapTeacherId")
    default Teacher mapTeacherId(Long teacherId) {
        if (teacherId == null) return null;
        TeacherRepository teacherRepository = ApplicationContextProvider.getBean(TeacherRepository.class);
        return teacherRepository.findById(teacherId)
                .orElseThrow(() -> new ObjectNotFoundException("Teacher not found with id: " + teacherId));
    }

    @Named("mapModuleIds")
    default List<CourseModule> mapModuleIds(List<Long> moduleIds) {
        if (moduleIds == null) return null;
        CourseModuleRepository courseModuleRepository = ApplicationContextProvider.getBean(CourseModuleRepository.class);
        return moduleIds.stream()
                .map(moduleId -> courseModuleRepository.findById(moduleId)
                        .orElseThrow(() -> new ObjectNotFoundException("Course Module not found with id: " + moduleId)))
                .collect(Collectors.toList());
    }

    @Named("mapCourseCategoryIds")
    default List<CourseCategory> mapCourseCategoryIds(List<Long> courseCategoryIds) {
        if (courseCategoryIds == null) return null;
        CourseCategoryRepository courseCategoryRepository = ApplicationContextProvider.getBean(CourseCategoryRepository.class);
        return courseCategoryIds.stream()
                .map(courseCategoryId -> courseCategoryRepository.findById(courseCategoryId)
                        .orElseThrow(() -> new ObjectNotFoundException("Course Category not found with id: " + courseCategoryId)))
                .collect(Collectors.toList());
    }
}


