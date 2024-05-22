package unifor.devweb.project.freelearn.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.exception.AccessDeniedException;
import unifor.devweb.project.freelearn.exception.ObjectNotFoundException;
import unifor.devweb.project.freelearn.infra.security.util.SecurityUtils;
import unifor.devweb.project.freelearn.repository.CourseRepository;

@Service
@Log4j2
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final SecurityUtils securityUtils;

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

        if (updatedCourse.getCourseCategories() != null) {
            existingCourse.getCourseCategories().clear();
            existingCourse.getCourseCategories().addAll(updatedCourse.getCourseCategories());
        }


        if (updatedCourse.getModules() != null) {
            existingCourse.getModules().clear();
            existingCourse.getModules().addAll(updatedCourse.getModules());
        }

    }

    public void delete(long id) {
        courseRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public boolean canAuthenticatedUserToModifyThisCourse(long courseId) {
        Course course = findByIdOrThrowBadRequestException(courseId);
        String authenticatedUserEmail = securityUtils.authenticatedUser().getEmail();

        boolean canAuthUser = securityUtils.isAdmin() || course.getTeacher().getUser().getEmail().equals(authenticatedUserEmail);

        if (!canAuthUser) {
            throw new AccessDeniedException("You do not have permission to modify this course");
        }

        return canAuthUser;
    }


}
