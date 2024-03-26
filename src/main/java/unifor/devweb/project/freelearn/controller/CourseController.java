package unifor.devweb.project.freelearn.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.services.CourseService;

import java.util.List;

@RestController
@RequestMapping("courses")
@Log4j2
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<Page<Course>> list(Pageable pageable) {
        return ResponseEntity.ok(courseService.listAll(pageable));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Course>> listAll() {
        return ResponseEntity.ok(courseService.listAllNonPageable());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Course> findById(@PathVariable long id) {
        return ResponseEntity.ok(courseService.findByIdOrThrowBadRequestException(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Course course) {
        courseService.replace(course);
        return ResponseEntity.noContent().build();
    }
}
