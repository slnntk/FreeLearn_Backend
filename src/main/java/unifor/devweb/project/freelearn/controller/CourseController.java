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
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.requests.course.CourseGetRequest;
import unifor.devweb.project.freelearn.domain.requests.course.CoursePostRequest;
import unifor.devweb.project.freelearn.domain.requests.course.CoursePutRequest;
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

    @GetMapping("/client/")
    public ResponseEntity<Page<CourseGetRequest>> listToClient(Pageable pageable) {
        return ResponseEntity.ok(courseService.listAllToClient(pageable));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<Course>> listAll() {
        return ResponseEntity.ok(courseService.listAllNonPageable());
    }

    @GetMapping(path = "/client/all")
    public ResponseEntity<List<CourseGetRequest>> listAllNonPageableToClient() {
        return ResponseEntity.ok(courseService.listAllNonPageableToClient());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Course> findById(@PathVariable long id) {
        return ResponseEntity.ok(courseService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/client/{id}")
    public ResponseEntity<CourseGetRequest> findByIdToClient(@PathVariable long id) {
        return ResponseEntity.ok(courseService.findByIdToClient(id));
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
        System.out.println(savedCourse);
        return new ResponseEntity<>(savedCourse, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Void> replace(@PathVariable long id, @RequestBody @Valid CoursePutRequest request) {
        System.out.println(id);
        System.out.println(request);
        Course existingCourse = courseService.findByIdOrThrowBadRequestException(id);
        System.out.println(existingCourse);
        Course updatedCourse = courseMapper.toCourse(existingCourse, request);
        System.out.println(updatedCourse);

        existingCourse.getModules().clear();

        List<CourseModule> newModules = courseMapper.mapCourseModulesReturnsList(updatedCourse, request.getModuleIds());
        existingCourse.getModules().addAll(newModules);

        courseService.replace(existingCourse);
        return ResponseEntity.noContent().build();
    }


}
