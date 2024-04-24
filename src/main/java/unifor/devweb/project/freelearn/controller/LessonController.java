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
import unifor.devweb.project.freelearn.domain.entities.Lesson;
import unifor.devweb.project.freelearn.dto.LessonDTO;
import unifor.devweb.project.freelearn.exception.BadRequestException;
import unifor.devweb.project.freelearn.mapper.LessonMapper;
import unifor.devweb.project.freelearn.services.LessonService;

import java.util.List;

@RestController
@RequestMapping("lessons")
@Log4j2
@RequiredArgsConstructor
public class LessonController {

    private final CycleAvoidingMappingContext context;
    private final LessonService lessonService;
    private final LessonMapper lessonMapper;

    @GetMapping
    public ResponseEntity<Page<LessonDTO>> list(@RequestParam("courseModuleId") Long courseModuleId, Pageable pageable) {
        Page<Lesson> lessonPage = lessonService.listAll(courseModuleId, pageable);
        Page<LessonDTO> lessonDTOPage = lessonPage.map(lessonMapper::toDTO);
        return ResponseEntity.ok(lessonDTOPage);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<LessonDTO>> listAll(@RequestParam("courseModuleId") Long courseModuleId) {
        List<Lesson> lessonList = (List<Lesson>) lessonService.listAllNonPageable(courseModuleId);
        List<LessonDTO> lessonDTOList = lessonList.stream()
                .map(lessonMapper::toDTO)
                .toList();
        return ResponseEntity.ok(lessonDTOList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<LessonDTO> findById(@PathVariable Long id) {
        Lesson lesson = lessonService.findByIdOrThrowBadRequestException(id);
        LessonDTO lessonDTO = lessonMapper.toDTO(lesson);
        return ResponseEntity.ok(lessonDTO);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<LessonDTO> save(@Valid @RequestParam("courseModuleId") Long courseModuleId, @RequestBody LessonDTO lessonDTO) {
        if (!lessonDTO.getModuleId().equals(courseModuleId)) {
            throw new BadRequestException("The courseModuleId must be the same in the request parameter and in the request body!");
        }
        lessonDTO.setModuleId(courseModuleId);
        Lesson lesson = lessonMapper.toEntity(lessonDTO, context);
        Lesson savedLesson = lessonService.save(lesson.getModule().getId(), lesson);
        LessonDTO savedLessonDTO = lessonMapper.toDTO(savedLesson);
        return new ResponseEntity<>(savedLessonDTO, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Void> replace(@PathVariable Long id, @RequestBody @Valid LessonDTO lessonDTO) {
        Lesson existingLesson = lessonService.findByIdOrThrowBadRequestException(id);
        if (!existingLesson.getId().equals(lessonDTO.getId())) {
            throw new BadRequestException("The lesson ID in the request body must match the ID in the URL!");
        }
        Lesson updatedLesson = lessonMapper.toEntity(lessonDTO, context);
        lessonService.replace(updatedLesson);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
