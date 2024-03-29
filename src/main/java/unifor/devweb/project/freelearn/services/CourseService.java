package unifor.devweb.project.freelearn.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.exception.ObjectNotFoundException;
import unifor.devweb.project.freelearn.repository.CourseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Page<Course> listAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    public List<Course> listAllNonPageable() {
        return courseRepository.findAll();
    }


    public Course findByIdOrThrowBadRequestException(long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Course not Found"));
    }

    @Transactional
    public Course save(Course course) {
        System.out.println(course);
        return courseRepository.save(course);
    }

    public void delete(long id) {
        courseRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(Course course) {
        Course savedCourse = findByIdOrThrowBadRequestException(course.getId());
        course.setId(savedCourse.getId());
        courseRepository.save(course);
    }
}
