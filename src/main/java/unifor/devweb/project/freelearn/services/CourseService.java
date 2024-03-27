package unifor.devweb.project.freelearn.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.exception.ObjectNotFoundException;
import unifor.devweb.project.freelearn.repository.CourseRepository;
import unifor.devweb.project.freelearn.requests.Course.CoursePostRequestBody;
import unifor.devweb.project.freelearn.requests.Course.CoursePutRequestBody;
import unifor.devweb.project.freelearn.mapper.CourseMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper = CourseMapper.INSTANCE;

    public Page<Course> listAll(Pageable pageable) {
        System.out.println(pageable);
        return courseRepository.findAll(pageable);
    }

    public List<Course> listAllNonPageable() {
        return courseRepository.findAll();
    }

    public Course findByIdOrThrowBadRequestException(long id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Course not Found with id: " + id));
    }

    public Course save(CoursePostRequestBody request) {
        System.out.println(request.toString());
        Course course = courseMapper.INSTANCE.toCourse(request);
        return courseRepository.save(course);
    }

    public void replace(Long id, CoursePutRequestBody request) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));

        Course updatedCourse = courseMapper.INSTANCE.toCourse(request);
        updatedCourse.setId(existingCourse.getId());

        courseRepository.save(updatedCourse);
    }

    public void delete(Long id) {
        courseRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}
