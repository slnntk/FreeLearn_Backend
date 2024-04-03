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
import unifor.devweb.project.freelearn.domain.entities.Lesson;
import unifor.devweb.project.freelearn.domain.requests.lesson.LessonGetRequest;
import unifor.devweb.project.freelearn.domain.requests.lesson.LessonPostRequest;
import unifor.devweb.project.freelearn.domain.requests.lesson.LessonPutRequest;
import unifor.devweb.project.freelearn.exception.BadRequestException;
import unifor.devweb.project.freelearn.mapper.LessonMapperImpl;
import unifor.devweb.project.freelearn.services.LessonService;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("lessons")
@Log4j2
@RequiredArgsConstructor
public class LessonController {


    private final LessonService lessonService;
    private final LessonMapperImpl lessonMapper;

    @GetMapping
    public ResponseEntity<Page<Lesson>> list(@RequestParam("courseModuleId") Long courseModuleId, Pageable pageable) {
        return ResponseEntity.ok(lessonService.listAll(courseModuleId, pageable));
    }

    @GetMapping("/client")
    public ResponseEntity<Page<LessonGetRequest>> listToClient(@RequestParam("courseModuleId") Long courseModuleId, Pageable pageable) {
        Page<Lesson> couseModulePage = lessonService.listAll(courseModuleId, pageable);
        Page<LessonGetRequest> couseModuleGetRequestPage = couseModulePage.map(lessonMapper::fromLessonToGetRequest);
        return ResponseEntity.ok(couseModuleGetRequestPage);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<Lesson>> listAll(@RequestParam("courseModuleId") Long courseModuleId) {
        return ResponseEntity.ok(lessonService.listAllNonPageable(courseModuleId));
    }

    @GetMapping(path = "/client/all")
    public ResponseEntity<Iterable <LessonGetRequest>> listAllNonPageableToClient(@RequestParam("courseModuleId") Long courseModuleId) {
        List<Lesson> lessonList = (List<Lesson>) lessonService.listAllNonPageable(courseModuleId);
        List<LessonGetRequest> lessonGetRequestList = lessonList
                .stream()
                .map(lessonMapper::fromLessonToGetRequest)
                .toList();
        return ResponseEntity.ok(lessonGetRequestList);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Lesson> findById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/client/{id}")
    public ResponseEntity<LessonGetRequest> findByIdToClient(@PathVariable long id) {
        Lesson lesson = lessonService.findByIdOrThrowBadRequestException(id);
        LessonGetRequest lessonGetRequest = lessonMapper.fromLessonToGetRequest(lesson);
        return ResponseEntity.ok(lessonGetRequest);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<Lesson> save(@Valid @RequestParam("courseModuleId") Long courseModuleId, @RequestBody LessonPostRequest lesson) {
        if (!Objects.equals(lesson.getModuleId(), courseModuleId)){
            throw new BadRequestException("The courseModuleId must be the same in the request parameter and in the request body!");
        }
        lesson.setModuleId(courseModuleId);
        Lesson mappedLesson = lessonMapper.toLesson(lesson);
        Lesson savedLesson = lessonService.save(mappedLesson.getModule().getId(), mappedLesson);
        return new ResponseEntity<>(savedLesson, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@PathVariable Long id, @RequestBody LessonPutRequest lesson) {
        Lesson existingLesson = lessonService.findByIdOrThrowBadRequestException(id);
        lesson.setModuleId(id);
        Lesson updatedLesson = lessonMapper.toLesson(existingLesson, lesson);
        lessonService.replace(updatedLesson);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

