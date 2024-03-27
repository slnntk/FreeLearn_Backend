package unifor.devweb.project.freelearn.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.CourseCategory;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.entities.Teacher;
import unifor.devweb.project.freelearn.exception.ObjectNotFoundException;
import unifor.devweb.project.freelearn.mapper.CourseMapper;
import unifor.devweb.project.freelearn.repository.CourseCategoryRepository;
import unifor.devweb.project.freelearn.repository.CourseModuleRepository;
import unifor.devweb.project.freelearn.repository.CourseRepository;
import unifor.devweb.project.freelearn.repository.TeacherRepository;
import unifor.devweb.project.freelearn.requests.Course.CoursePostRequestBody;
import unifor.devweb.project.freelearn.requests.Course.CoursePutRequestBody;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;
    private final CourseCategoryRepository courseCategoryRepository;
    private final CourseModuleRepository courseModuleRepository;
    private final CourseMapper courseMapper = CourseMapper.INSTANCE;

    public Page<Course> listAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    public List<Course> listAllNonPageable() {
        List<Course> courses = courseRepository.findAll();
        for (Course course : courses) {
            System.out.println(course); // Supondo que o método toString() de Course seja adequado para impressão
        }
        return courses;
    }

    public Course findByIdOrThrowBadRequestException(long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Course not Found with id: " + id));
    }

    @Transactional
    public Course save(CoursePostRequestBody request) {
        // Mapear a requisição para a entidade Course
        Course course = courseMapper.toCourse(request);

        // Verificar se o teacherId é válido e atribuí-lo ao curso
        if (request.getTeacherId() != null) {
            Teacher teacher = teacherRepository.findById(request.getTeacherId())
                    .orElseThrow(() -> new ObjectNotFoundException("Teacher not found with id: " + request.getTeacherId()));
            course.setTeacher(teacher);
        }

        // Verificar e atribuir os módulos ao curso
        if (request.getModuleIds() != null && !request.getModuleIds().isEmpty()) {
            List<CourseModule> modules = courseModuleRepository.findAllById(request.getModuleIds());
            course.setModules(modules);
        }

        // Verificar e atribuir as categorias de curso ao curso
        if (request.getCourseCategoryIds() != null && !request.getCourseCategoryIds().isEmpty()) {
            List<CourseCategory> categories = courseCategoryRepository.findAllById(request.getCourseCategoryIds());
            course.setCourseCategories(categories);
        }

        // Salvar o curso no banco de dados
        return courseRepository.save(course);
    }

    @Transactional
    public void replace(Long id, CoursePutRequestBody request) {
        Course existingCourse = findByIdOrThrowBadRequestException(id);
        Teacher teacher = getTeacherOrThrowNotFound(request.getTeacherId());
        List<CourseCategory> courseCategories = getCourseCategoriesOrThrowNotFound(request.getCourseCategoryIds());
        List<CourseModule> modules = getCourseModulesOrThrowNotFound(request.getModuleIds());

        Course updatedCourse = courseMapper.toCourse(request);
        updatedCourse.setId(id);
        updatedCourse.setTeacher(teacher);
        updatedCourse.setCourseCategories(courseCategories);
        updatedCourse.setModules(modules);

        courseRepository.save(updatedCourse);
    }


    public void delete(Long id) {
        courseRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    @Transactional
    protected Teacher getTeacherOrThrowNotFound(Long teacherId) {
        return getObjectByIdOrThrowNotFound(teacherRepository, teacherId, "Teacher");
    }

    @Transactional
    protected List<CourseCategory> getCourseCategoriesOrThrowNotFound(List<Long> categoryIds) {
        return getObjectsByIdsOrThrowNotFound(courseCategoryRepository, categoryIds, "Course Category");
    }

    @Transactional
    protected List<CourseModule> getCourseModulesOrThrowNotFound(List<Long> moduleIds) {
        return getObjectsByIdsOrThrowNotFound(courseModuleRepository, moduleIds, "Course Module");
    }

    @Transactional
    protected <T> T getObjectByIdOrThrowNotFound(CrudRepository<T, Long> repository, Long id, String entityName) {
        return repository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(entityName + " not found with id: " + id));
    }
    @Transactional
    protected <T> List<T> getObjectsByIdsOrThrowNotFound(CrudRepository<T, Long> repository, List<Long> ids, String entityName) {
        return (List<T>) repository.findAllById(ids);
    }
}
