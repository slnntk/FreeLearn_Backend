package unifor.devweb.project.freelearn.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unifor.devweb.project.freelearn.domain.entities.Review;
import unifor.devweb.project.freelearn.domain.requests.review.ReviewGetRequest;
import unifor.devweb.project.freelearn.domain.requests.review.ReviewPostRequest;
import unifor.devweb.project.freelearn.domain.requests.review.ReviewPutRequest;
import unifor.devweb.project.freelearn.mapper.ReviewMapperImpl;
import unifor.devweb.project.freelearn.services.ReviewService;

import java.util.List;

@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapperImpl reviewMapper;

    @GetMapping
    public ResponseEntity<Page<Review>> list(Pageable pageable) {
        return ResponseEntity.ok(reviewService.listAll(pageable));
    }

    @GetMapping("/client")
    public ResponseEntity<Page<ReviewGetRequest>> listToClient(Pageable pageable) {
        Page<Review> reviewPage = reviewService.listAll(pageable);
        Page<ReviewGetRequest> reviewGetRequestPage = reviewPage.map(reviewMapper::fromReviewToGetRequest);
        return ResponseEntity.ok(reviewGetRequestPage);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable <Review>> listAll() {
        return ResponseEntity.ok(reviewService.listAllNonPageable());
    }

    @GetMapping(path = "/client/all")
    public ResponseEntity<Iterable <ReviewGetRequest>> listAllNonPageableToClient() {
        List<Review> reviewList = (List<Review>) reviewService.listAllNonPageable();
        List<ReviewGetRequest> reviewGetRequestList = reviewList
                .stream()
                .map(reviewMapper::fromReviewToGetRequest)
                .toList();
        return ResponseEntity.ok(reviewGetRequestList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Review> findById(@PathVariable long id) {
        return ResponseEntity.ok(reviewService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/client/{id}")
    public ResponseEntity<ReviewGetRequest> findByIdToClient(@PathVariable long id) {
        Review review = reviewService.findByIdOrThrowBadRequestException(id);
        ReviewGetRequest reviewGetRequest = reviewMapper.fromReviewToGetRequest(review);
        return ResponseEntity.ok(reviewGetRequest);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Review> save(@Valid @RequestBody ReviewPostRequest request) {
        Review review = reviewMapper.toReview(request);
        Review savedReview = reviewService.save(review);
        return new ResponseEntity<>(savedReview, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Void> replace(@PathVariable long id, @RequestBody @Valid ReviewPutRequest request) {
        Review existingReview = reviewService.findByIdOrThrowBadRequestException(id);

        request.setCourseId(existingReview.getCourse().getId());
        request.setStudentId(existingReview.getStudent().getId());
        request.setTeacherId(existingReview.getTeacher().getId());

        Review updatedReview = reviewMapper.toReview(existingReview, request);

        reviewService.replace(updatedReview);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
