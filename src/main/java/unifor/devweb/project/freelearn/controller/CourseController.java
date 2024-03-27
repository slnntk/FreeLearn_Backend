package unifor.devweb.project.freelearn.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.services.CourseService;
import unifor.devweb.project.freelearn.requests.Course.CoursePostRequestBody;
import unifor.devweb.project.freelearn.requests.Course.CoursePutRequestBody;

import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Course> save(@RequestBody CoursePostRequestBody request) {
        Course createdCourse = courseService.save(request);
        return ResponseEntity.created(URI.create("/courses/" + createdCourse.getId())).body(createdCourse);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@PathVariable Long id, @RequestBody CoursePutRequestBody request) {
        courseService.replace(id, request);
        return ResponseEntity.noContent().build();
    }
}
