package unifor.devweb.project.freelearn.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import unifor.devweb.project.freelearn.domain.entities.CourseCategory;
import unifor.devweb.project.freelearn.domain.entities.CourseCourseCategory;
import unifor.devweb.project.freelearn.exception.ObjectNotFoundException;
import unifor.devweb.project.freelearn.repository.CourseCategoryRepository;
import unifor.devweb.project.freelearn.repository.CourseCourseCategoryRepository;

@Service
@RequiredArgsConstructor
public class CourseCategoryService {

    private final CourseCategoryRepository courseCategoryRepository;
    private final CourseCourseCategoryRepository courseCourseCategoryRepository;

    public Page<CourseCategory> listAll(Pageable pageable) {
        return courseCategoryRepository.findAll(pageable);
    }

    public Iterable<CourseCategory> listAllNonPageable() {
        return courseCategoryRepository.findAll();
    }

    public CourseCategory findByIdOrThrowBadRequestException(Long categoryId) {
        return courseCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Course Category not found"));
    }

    public CourseCourseCategory findCourseCourseCategoryById(Long categoryId) {
        return courseCourseCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new ObjectNotFoundException("Course Category not found"));
    }


    @Transactional
    public CourseCategory save(CourseCategory category) {
        return courseCategoryRepository.save(category);
    }

    public void replace(CourseCategory courseCategory) {
        CourseCategory existingCategory = findByIdOrThrowBadRequestException(courseCategory.getId());
        replaceData(courseCategory, existingCategory);
        courseCategoryRepository.save(existingCategory);
    }

    private void replaceData(CourseCategory updatedCategory, CourseCategory existingCategory) {
        existingCategory.setName(updatedCategory.getName());
        existingCategory.setDescription(updatedCategory.getDescription());
        existingCategory.setCourses(updatedCategory.getCourses());
    }

    public void delete(Long categoryId) {
        CourseCategory category = findByIdOrThrowBadRequestException(categoryId);
        courseCategoryRepository.delete(category);
    }
}

