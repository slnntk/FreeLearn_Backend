package unifor.devweb.project.freelearn.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unifor.devweb.project.freelearn.domain.entities.CourseModule;
import unifor.devweb.project.freelearn.domain.requests.coursemodule.CourseModuleGetRequest;
import unifor.devweb.project.freelearn.domain.requests.coursemodule.CourseModulePostRequest;
import unifor.devweb.project.freelearn.domain.requests.coursemodule.CourseModulePutRequest;
import unifor.devweb.project.freelearn.exception.BadRequestException;
import unifor.devweb.project.freelearn.mapper.CourseModuleMapperImpl;
import unifor.devweb.project.freelearn.services.CourseModuleService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("course-modules")
@Log4j2
@RequiredArgsConstructor
public class CourseModuleController {

    private final CourseModuleService courseModuleService;
    private final CourseModuleMapperImpl courseModuleMapper;

    @GetMapping
    public ResponseEntity<Page<CourseModule>> list(@RequestParam("courseId") Long courseId, Pageable pageable) {
        return ResponseEntity.ok(courseModuleService.listAll(courseId, pageable));
    }

    @GetMapping("/client")
    public ResponseEntity<Page<CourseModuleGetRequest>> listToClient(@RequestParam("courseId") Long courseId, Pageable pageable) {
        Page<CourseModule> coursePage = courseModuleService.listAll(courseId, pageable);
        Page<CourseModuleGetRequest> courseGetRequestPage = coursePage.map(courseModuleMapper::fromCourseModuleToGetRequest);
        return ResponseEntity.ok(courseGetRequestPage);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<CourseModule>> listAll(@RequestParam("courseId") Long courseId) {
        return ResponseEntity.ok(courseModuleService.listAllNonPageable(courseId));
    }

    @GetMapping(path = "/client/all")
    public ResponseEntity<Iterable <CourseModuleGetRequest>> listAllNonPageableToClient(@RequestParam("courseId") Long courseId) {
        List<CourseModule> courseModuleList = (List<CourseModule>) courseModuleService.listAllNonPageable(courseId);
        List<CourseModuleGetRequest> courseModuleGetRequestList = courseModuleList
                .stream()
                .map(courseModuleMapper::fromCourseModuleToGetRequest)
                .toList();
        return ResponseEntity.ok(courseModuleGetRequestList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseModule> findById(@PathVariable Long id) {
        return ResponseEntity.ok(courseModuleService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/client/{id}")
    public ResponseEntity<CourseModuleGetRequest> findByIdToClient(@PathVariable long id) {
        CourseModule course = courseModuleService.findByIdOrThrowBadRequestException(id);
        CourseModuleGetRequest courseModuleGetRequest = courseModuleMapper.fromCourseModuleToGetRequest(course);
        return ResponseEntity.ok(courseModuleGetRequest);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CourseModule> save(@Valid @RequestParam("courseId") Long courseId, @RequestBody CourseModulePostRequest module) {
        if (!Objects.equals(module.getCourseId(), courseId)){
            throw new BadRequestException("The courseId must be the same in the request parameter and in the request body!");
        }
        module.setCourseId(courseId);
        CourseModule courseModule = courseModuleMapper.toCourseModule(module);
        CourseModule savedCourseModule = courseModuleService.save(courseModule.getCourse().getId(), courseModule);
        return new ResponseEntity<>(savedCourseModule, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@PathVariable Long id, @RequestBody CourseModulePutRequest module) {
        CourseModule existingCourseModule = courseModuleService.findByIdOrThrowBadRequestException(id);
        module.setCourseId(id);
        CourseModule updatedCourseModule = courseModuleMapper.toCourseModule(existingCourseModule, module);
        courseModuleService.replace(updatedCourseModule);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseModuleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
