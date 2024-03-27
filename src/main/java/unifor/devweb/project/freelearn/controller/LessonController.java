package unifor.devweb.project.freelearn.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unifor.devweb.project.freelearn.domain.entities.Lesson;
import unifor.devweb.project.freelearn.services.LessonService;

@RestController
@RequestMapping("lessons")
@Log4j2
@RequiredArgsConstructor
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<Page<Lesson>> list(@RequestParam("moduleId") Long moduleId, Pageable pageable) {
        return ResponseEntity.ok(lessonService.listAll(moduleId, pageable));
    }

    @GetMapping(path = "/all")
    public ResponseEntity<Iterable<Lesson>> listAll(@RequestParam("moduleId") Long moduleId) {
        return ResponseEntity.ok(lessonService.listAllNonPageable(moduleId));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Lesson> findById(@PathVariable Long id) {
        return ResponseEntity.ok(lessonService.findByIdOrThrowBadRequestException(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lessonService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@PathVariable Long id, @RequestBody Lesson lesson) {
        lesson.setId(id);
        lessonService.replace(id, lesson);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<Lesson> save(@RequestParam("moduleId") Long moduleId, @RequestBody Lesson lesson) {
        Lesson createdLesson = lessonService.save(moduleId, lesson);
        return ResponseEntity.ok(createdLesson);
    }
}
