package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.entities.Review;
import unifor.devweb.project.freelearn.domain.entities.Student;
import unifor.devweb.project.freelearn.domain.entities.StudentCourse;
import unifor.devweb.project.freelearn.dto.StudentDTO;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    CourseMapper courseMapper = Mappers.getMapper(CourseMapper.class);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "enrolledCourses", target = "enrolledCourseIds", qualifiedByName = "mapCoursesToDTO")
    @Mapping(source = "reviews", target = "courseReviewIds", qualifiedByName = "mapReviewsToDTO")
    StudentDTO toDTO(Student student);

    @Named("mapCoursesToDTO")
    default List<Long> mapCoursesToDTO(List<StudentCourse> studentCourses) {
        if (studentCourses == null || studentCourses.isEmpty()) {
            return new ArrayList<>();
        }
        return studentCourses.stream()
                .map(StudentCourse::getCourse)
                .map(Course::getId)
                .toList();
    }

    @Named("mapReviewsToDTO")
    default List<Long> mapReviewsToDTO(List<Review> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return new ArrayList<>();
        }
        return reviews.stream()
                .map(Review::getId)
                .toList();
    }

    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "enrolledCourses", source = "enrolledCourseIds", qualifiedByName = "mapDTOsToStudentCourses")
    @Mapping(target = "reviews", source = "courseReviewIds", qualifiedByName = "mapDTOsToReviews")
    Student toEntity(StudentDTO studentDTO, @Context CycleAvoidingMappingContext context);

    @Named("mapDTOsToStudentCourses")
    default List<StudentCourse> mapDTOsToStudentCourses(List<Long> courseIds, @Context CycleAvoidingMappingContext context) {
        if (courseIds == null || courseIds.isEmpty()) {
            return null;
        }
        return courseIds.stream()
                .map(courseId -> {
                    Course course = context.getCourseService().findByIdOrThrowBadRequestException(courseId);
                    StudentCourse studentCourse = new StudentCourse();
                    studentCourse.setCourse(course);
                    return studentCourse;
                })
                .toList();
    }

    @Named("mapDTOsToReviews")
    default List<Review> mapDTOsToReviews(List<Long> reviewIds, @Context CycleAvoidingMappingContext context) {
        if (reviewIds == null || reviewIds.isEmpty()) {
            return null;
        }
        return reviewIds.stream()
                .map(reviewId -> {
                    Review review = context.getReviewService().findByIdOrThrowBadRequestException(reviewId);
                    return review;
                })
                .toList();
    }
}
