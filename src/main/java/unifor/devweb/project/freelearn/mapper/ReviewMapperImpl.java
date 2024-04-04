package unifor.devweb.project.freelearn.mapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import unifor.devweb.project.freelearn.domain.entities.*;
import unifor.devweb.project.freelearn.domain.requests.review.ReviewGetRequest;
import unifor.devweb.project.freelearn.domain.requests.review.ReviewPostRequest;
import unifor.devweb.project.freelearn.domain.requests.review.ReviewPutRequest;
import unifor.devweb.project.freelearn.repository.CourseRepository;
import unifor.devweb.project.freelearn.repository.StudentRepository;
import unifor.devweb.project.freelearn.repository.TeacherRepository;

@Component
@Log4j2
@RequiredArgsConstructor
public class ReviewMapperImpl {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final TeacherRepository teacherRepository;

    public Review toReview(ReviewPostRequest reviewPostRequest) {
        log.info(reviewPostRequest);
        if (reviewPostRequest == null) {
            return null;
        }

        Review review = new Review();
        mapReviewFields(review, reviewPostRequest);

        return review;
    }

    public Review toReview(Review existingReview, ReviewPutRequest reviewPutRequest) {
        log.info(reviewPutRequest);
        if (existingReview == null || reviewPutRequest == null) {
            return null;
        }

        mapReviewFields(existingReview, reviewPutRequest);

        return existingReview;
    }

    public ReviewGetRequest fromReviewToGetRequest(Review review) {
        if (review == null) {
            return null;
        }

        ReviewGetRequest reviewGetRequest = new ReviewGetRequest();
        reviewGetRequest.setComment(review.getComment());
        reviewGetRequest.setStudentId(review.getStudent() != null ? review.getStudent().getId() : null);
        reviewGetRequest.setCourseId(review.getCourse() != null ? review.getCourse().getId() : null);
        reviewGetRequest.setTeacherId(review.getTeacher() != null ? review.getTeacher().getId() : null);

        return reviewGetRequest;
    }

    private void mapReviewFields(Review review, ReviewPostRequest reviewPostRequest) {
        review.setComment(reviewPostRequest.getComment());
        review.setStudent(studentRepository.findById(reviewPostRequest.getStudentId()).orElse(null));
        review.setCourse(courseRepository.findById(reviewPostRequest.getCourseId()).orElse(null));
        review.setTeacher(teacherRepository.findById(reviewPostRequest.getTeacherId()).orElse(null));
    }

    private void mapReviewFields(Review review, ReviewPutRequest reviewPutRequest) {
        review.setComment(reviewPutRequest.getComment());
        review.setStudent(studentRepository.findById(reviewPutRequest.getStudentId()).orElse(null));
        review.setCourse(courseRepository.findById(reviewPutRequest.getCourseId()).orElse(null));
        review.setTeacher(teacherRepository.findById(reviewPutRequest.getTeacherId()).orElse(null));
    }
}
