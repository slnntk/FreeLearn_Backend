package unifor.devweb.project.freelearn.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.services.CourseModuleService;

@RestController
@RequestMapping("course-modules")
@Log4j2
@RequiredArgsConstructor
public class CourseModuleController {

    private final CourseModuleService courseModuleService;

    @GetMapping
    public ResponseEntity<Page<CourseModule>> list(@RequestParam("courseId") Long courseId, Pageable pageable) {
        return ResponseEntity.ok(courseModuleService.listAll(courseId, pageable));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<CourseModule>> listAll(@RequestParam("courseId") Long courseId) {
        return ResponseEntity.ok(courseModuleService.listAllNonPageable(courseId));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseModule> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseModuleService.findByIdOrThrowBadRequestException(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseModuleService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@PathVariable Long id, @RequestBody CourseModule module) {
        module.setId(id);
        courseModuleService.replace(id, module);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<CourseModule> create(@RequestParam("courseId") Long courseId, @RequestBody CourseModule module) {
        CourseModule createdModule = courseModuleService.save(courseId, module);
        return ResponseEntity.ok(createdModule);
    }


}
