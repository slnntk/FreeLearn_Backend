package unifor.devweb.project.freelearn.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.exception.ObjectNotFoundException;
import unifor.devweb.project.freelearn.repository.CourseModuleRepository;
import unifor.devweb.project.freelearn.repository.CourseRepository;

@Service
@RequiredArgsConstructor
public class CourseModuleService {

    private final CourseModuleRepository courseModuleRepository;
    private final CourseRepository courseRepository;

    public Page<CourseModule> listAll(Long courseId, Pageable pageable) {
        return courseModuleRepository.findAllByCourseIdPageable(courseId, pageable);
    }

    public Iterable<CourseModule> listAllNonPageable(Long courseId) {
        return courseModuleRepository.findAllByCourseId(courseId);
    }

    public CourseModule findByIdOrThrowBadRequestException(Long moduleId) {
        return courseModuleRepository.findById(moduleId)
                .orElseThrow(() -> new ObjectNotFoundException("Course module not found"));
    }

    @Transactional
    public CourseModule save(Long courseId, CourseModule module) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ObjectNotFoundException("Course not found"));
        module.setCourse(course);
        return courseModuleRepository.save(module);
    }

    public void replace(CourseModule updatedModule) {
        CourseModule existingModule = findByIdOrThrowBadRequestException(updatedModule.getId());
        replaceData(updatedModule, existingModule);
        courseModuleRepository.save(existingModule);
    }

    private void replaceData(CourseModule updatedModule, CourseModule existingModule) {
        existingModule.setTitle(updatedModule.getTitle());
        existingModule.setDescription(updatedModule.getDescription());
        existingModule.setSequenceNumber(updatedModule.getSequenceNumber());
        existingModule.setLessons(updatedModule.getLessons());
    }


    public void delete(Long moduleId) {
        CourseModule module = findByIdOrThrowBadRequestException(moduleId);
        courseModuleRepository.delete(module);
    }
}
