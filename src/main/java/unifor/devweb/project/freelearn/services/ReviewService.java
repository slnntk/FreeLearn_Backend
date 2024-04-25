package unifor.devweb.project.freelearn.services;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import unifor.devweb.project.freelearn.domain.entities.Review;
import unifor.devweb.project.freelearn.exception.ObjectNotFoundException;
import unifor.devweb.project.freelearn.repository.ReviewRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Page<Review> listAll(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    public Iterable<Review> listAllNonPageable() {
        return reviewRepository.findAll();
    }

    public Review findByIdOrThrowBadRequestException(long id) {
        return reviewRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException("Review not Found"));
    }

    @Transactional
    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    @Transactional
    public void replace(Review updatedReview) {
        Review existingReview = findByIdOrThrowBadRequestException(updatedReview.getId());
        replaceData(updatedReview, existingReview);
        reviewRepository.save(existingReview);
    }

    private void replaceData(Review updatedReview, Review existingReview) {
        existingReview.setComment(updatedReview.getComment());
    }

    public void delete(long id) {
        reviewRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}
