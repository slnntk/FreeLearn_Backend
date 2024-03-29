package unifor.devweb.project.freelearn.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.domain.requests.course.CoursePostRequest;
import unifor.devweb.project.freelearn.mapper.CourseMapperImpl;
import unifor.devweb.project.freelearn.services.CourseService;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("courses")
@Log4j2
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapperImpl courseMapper;

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

    @Transactional
    @PostMapping
    public ResponseEntity<Course> save(@Valid @RequestBody CoursePostRequest request) {
        Course course = courseMapper.toCourse(request);
        System.out.println(course);
        Course savedCourse = courseService.save(course);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody Course course) {
        courseService.replace(course);
        return ResponseEntity.noContent().build();
    }
}
