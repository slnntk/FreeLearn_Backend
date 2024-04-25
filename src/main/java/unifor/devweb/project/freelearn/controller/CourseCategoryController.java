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
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.CourseCategory;
import unifor.devweb.project.freelearn.dto.CourseCategoryDTO;
import unifor.devweb.project.freelearn.mapper.CourseCategoryMapper;
import unifor.devweb.project.freelearn.services.CourseCategoryService;

import java.util.List;

@RestController
@RequestMapping("course-categories")
@Log4j2
@RequiredArgsConstructor
public class CourseCategoryController {

    private final CycleAvoidingMappingContext context;
    private final CourseCategoryService courseCategoryService;
    private final CourseCategoryMapper courseCategoryMapper;

    @GetMapping
    public ResponseEntity<Page<CourseCategoryDTO>> list(Pageable pageable) {
        Page<CourseCategory> courseCategories = courseCategoryService.listAll(pageable);
        Page<CourseCategoryDTO> courseCategoryDTOs = courseCategories.map(courseCategoryMapper::toDTO);
        return ResponseEntity.ok(courseCategoryDTOs);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<CourseCategoryDTO>> listAll() {
        List<CourseCategory> courseCategories = (List<CourseCategory>) courseCategoryService.listAllNonPageable();
        List<CourseCategoryDTO> courseCategoryDTOs = courseCategories.stream()
                .map(courseCategoryMapper::toDTO)
                .toList();
        return ResponseEntity.ok(courseCategoryDTOs);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseCategoryDTO> findById(@PathVariable long id) {
        CourseCategory category = courseCategoryService.findByIdOrThrowBadRequestException(id);
        CourseCategoryDTO categoryDTO = courseCategoryMapper.toDTO(category);
        return ResponseEntity.ok(categoryDTO);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CourseCategoryDTO> save(@Valid @RequestBody CourseCategoryDTO request) {
        CourseCategory category = courseCategoryMapper.toEntity(request);
        CourseCategory savedCourseCategory = courseCategoryService.save(category);
        CourseCategoryDTO savedCourseCategoryDTO = courseCategoryMapper.toDTO(savedCourseCategory);
        return new ResponseEntity<>(savedCourseCategoryDTO, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Void> replace(@PathVariable long id, @RequestBody @Valid CourseCategoryDTO request) {
        CourseCategory existingCategory = courseCategoryService.findByIdOrThrowBadRequestException(id);
        if (existingCategory.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        CourseCategory updatedCategory = courseCategoryMapper.toEntity(request);
        updatedCategory.setId(id);
        courseCategoryService.replace(updatedCategory);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        courseCategoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
