package unifor.devweb.project.freelearn.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.Review;
import unifor.devweb.project.freelearn.dto.ReviewDTO;
import unifor.devweb.project.freelearn.mapper.ReviewMapper;
import unifor.devweb.project.freelearn.services.ReviewService;

import java.util.List;

@RestController
@RequestMapping("reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final CycleAvoidingMappingContext context;
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @GetMapping
    public ResponseEntity<Page<ReviewDTO>> list(Pageable pageable) {
        Page<Review> reviewPage = reviewService.listAll(pageable);
        Page<ReviewDTO> reviewDTOPage = reviewPage.map(reviewMapper::toDTO);
        return ResponseEntity.ok(reviewDTOPage);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<ReviewDTO>> listAll() {
        List<Review> reviewList = (List<Review>) reviewService.listAllNonPageable();
        List<ReviewDTO> reviewDTOList = reviewList.stream()
                .map(reviewMapper::toDTO)
                .toList();
        return ResponseEntity.ok(reviewDTOList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ReviewDTO> findById(@PathVariable long id) {
        Review review = reviewService.findByIdOrThrowBadRequestException(id);
        ReviewDTO reviewDTO = reviewMapper.toDTO(review);
        return ResponseEntity.ok(reviewDTO);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ReviewDTO> save(@Valid @RequestBody ReviewDTO request) {
        Review review = reviewMapper.toEntity(request, context);
        Review savedReview = reviewService.save(review);
        ReviewDTO savedReviewDTO = reviewMapper.toDTO(savedReview);
        return new ResponseEntity<>(savedReviewDTO, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Void> replace(@PathVariable long id, @RequestBody @Valid ReviewDTO request) {
        Review existingReview = reviewService.findByIdOrThrowBadRequestException(id);
        Review updatedReview = reviewMapper.toEntity(request, context);
        updatedReview.setId(existingReview.getId());
        reviewService.replace(updatedReview);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
