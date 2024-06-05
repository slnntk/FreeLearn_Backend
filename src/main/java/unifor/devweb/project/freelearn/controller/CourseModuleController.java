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
import unifor.devweb.project.freelearn.dto.CourseModuleDTO;
import unifor.devweb.project.freelearn.exception.BadRequestException;
import unifor.devweb.project.freelearn.mapper.CourseModuleMapper;
import unifor.devweb.project.freelearn.services.CourseModuleService;

import java.util.List;

@RestController
@RequestMapping("course-modules")
@Log4j2
@RequiredArgsConstructor
public class CourseModuleController {

    private final CourseModuleService courseModuleService;
    private final CourseModuleMapper courseModuleMapper;

    @GetMapping
    public ResponseEntity<Page<CourseModuleDTO>> list(@RequestParam("courseId") Long courseId, Pageable pageable) {
        Page<CourseModule> coursePage = courseModuleService.listAll(courseId, pageable);
        Page<CourseModuleDTO> courseDTOPage = coursePage.map(courseModuleMapper::toDTO);
        return ResponseEntity.ok(courseDTOPage);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<CourseModuleDTO>> listAll(@RequestParam("courseId") Long courseId) {
        List<CourseModule> courseModuleList = (List<CourseModule>) courseModuleService.listAllNonPageable(courseId);
        List<CourseModuleDTO> courseModuleDTOList = courseModuleList.stream()
                .map(courseModuleMapper::toDTO)
                .toList();
        return ResponseEntity.ok(courseModuleDTOList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseModuleDTO> findById(@PathVariable Long id) {
        CourseModule courseModule = courseModuleService.findByIdOrThrowBadRequestException(id);
        CourseModuleDTO courseModuleDTO = courseModuleMapper.toDTO(courseModule);
        return ResponseEntity.ok(courseModuleDTO);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CourseModuleDTO> save(@Valid @RequestParam("courseId") Long courseId, @RequestBody CourseModuleDTO moduleDTO) {
        CourseModule courseModule = courseModuleMapper.toEntity(moduleDTO);
        CourseModule savedCourseModule = courseModuleService.save(courseId, courseModule);
        CourseModuleDTO savedCourseModuleDTO = courseModuleMapper.toDTO(savedCourseModule);
        return new ResponseEntity<>(savedCourseModuleDTO, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping(value = "/{id}", consumes = "application/json")
    public ResponseEntity<Void> replace(@PathVariable Long id, @RequestBody @Valid CourseModuleDTO moduleDTO) {
        CourseModule existingCourseModule = courseModuleService.findByIdOrThrowBadRequestException(id);
        if (!existingCourseModule.getId().equals(moduleDTO.getId())) {
            throw new BadRequestException("The module ID in the request body must match the ID in the URL!");
        }
        CourseModule updatedCourseModule = courseModuleMapper.toEntity(moduleDTO);
        courseModuleService.replace(updatedCourseModule);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseModuleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
