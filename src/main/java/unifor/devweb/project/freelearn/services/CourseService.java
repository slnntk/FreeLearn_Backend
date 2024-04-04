package unifor.devweb.project.freelearn.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.exception.ObjectNotFoundException;
import unifor.devweb.project.freelearn.repository.CourseRepository;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Page<Course> listAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    public Iterable<Course> listAllNonPageable() {
        return courseRepository.findAll();
    }

    public Course findByIdOrThrowBadRequestException(long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Course not Found"));
    }

    @Transactional
    public Course save(Course course) {
        return courseRepository.save(course);
    }

    @Transactional
    public void replace(Course updatedCourse) {
        Course existingCourse = findByIdOrThrowBadRequestException(updatedCourse.getId());
        replaceData(updatedCourse, existingCourse);
        courseRepository.save(existingCourse);
    }

    private void replaceData(Course updatedCourse, Course existingCourse) {
        existingCourse.setTitle(updatedCourse.getTitle());
        existingCourse.setDescription(updatedCourse.getDescription());
        existingCourse.setImageUrl(updatedCourse.getImageUrl());
        existingCourse.setLanguage(updatedCourse.getLanguage());
        existingCourse.setDurationHours(updatedCourse.getDurationHours());
        existingCourse.setLink(updatedCourse.getLink());
        existingCourse.setTeacher(updatedCourse.getTeacher());
        existingCourse.setCourseCategories(updatedCourse.getCourseCategories());
        existingCourse.setModules(updatedCourse.getModules());
        existingCourse.setEnrolledStudents(updatedCourse.getEnrolledStudents());
    }

    public void delete(long id) {
        courseRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}
