package unifor.devweb.project.freelearn.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.requests.course.CourseGetRequest;
import unifor.devweb.project.freelearn.exception.ObjectNotFoundException;
import unifor.devweb.project.freelearn.mapper.CourseMapperImpl;
import unifor.devweb.project.freelearn.repository.CourseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapperImpl courseMapper;

    public Page<Course> listAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    public Page<CourseGetRequest> listAllToClient(Pageable pageable) {
        Page<Course> coursePage = courseRepository.findAll(pageable);
        return coursePage
                .map(course -> courseMapper.fromCourseToGetRequest(course)
        );
    }

    public List<Course> listAllNonPageable() {
        return courseRepository.findAll();
    }

    public List<CourseGetRequest> listAllNonPageableToClient() {
        return courseRepository.findAll()
                .stream()
                .map(course ->
                     courseMapper.fromCourseToGetRequest(course)
                ).collect(Collectors.toList());

    }


    public CourseGetRequest findByIdToClient(long id) {
        return courseMapper.fromCourseToGetRequest(findByIdOrThrowBadRequestException(id));
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

    @Transactional
    public void replace(Course updatedCourse) {
        System.out.println(updatedCourse);
        Course existingCourse = findByIdOrThrowBadRequestException(updatedCourse.getId());
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
        courseRepository.save(existingCourse);
    }
}
