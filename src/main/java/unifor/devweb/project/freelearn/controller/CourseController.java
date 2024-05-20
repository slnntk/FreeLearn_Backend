package unifor.devweb.project.freelearn.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import unifor.devweb.project.freelearn.domain.entities.Course;
import unifor.devweb.project.freelearn.dto.CourseDTO;
import unifor.devweb.project.freelearn.exception.AccessDeniedException;
import unifor.devweb.project.freelearn.exception.BadRequestException;
import unifor.devweb.project.freelearn.mapper.CourseMapper;
import unifor.devweb.project.freelearn.services.CourseService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("courses")
@Log4j2
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    @GetMapping
    public ResponseEntity<Page<CourseDTO>> list(Pageable pageable) {
        Page<CourseDTO> courseDTOPage = courseService.listAll(pageable)
                .map(courseMapper::toDTO);
        return ResponseEntity.ok(courseDTOPage);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<CourseDTO>> listAll() {
        List<CourseDTO> courseDTOList = new ArrayList<>();
        courseService.listAllNonPageable().forEach(course -> courseDTOList.add(courseMapper.toDTO(course)));
        return ResponseEntity.ok(courseDTOList);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping(path = "/{id}")
    public ResponseEntity<CourseDTO> findById(@PathVariable long id) {
        CourseDTO courseDTO = courseMapper.toDTO(courseService.findByIdOrThrowBadRequestException(id));
        return ResponseEntity.ok(courseDTO);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CourseDTO> save(@Valid @RequestBody CourseDTO request) {
        CourseDTO savedCourseDTO = courseMapper.toDTO(courseService.save(courseMapper.toEntity(request)));
        return new ResponseEntity<>(savedCourseDTO, HttpStatus.CREATED);
    }

//    @Transactional
//    @PutMapping("/{id}")
//    public ResponseEntity<Void> replace(@PathVariable long id, @RequestBody @Valid CourseDTO request) {
//        CourseDTO updatedCourseDTO = courseMapper.toDTO(courseService.findByIdOrThrowBadRequestException(id));
//        log.info(updatedCourseDTO.toString());
//        if (updatedCourseDTO.getId() == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        request.setId(id);
//        Course course = courseMapper.toEntity(request);
//        courseService.replace(course);
//        log.info(course);
//        return ResponseEntity.noContent().build();
//    }

    @PreAuthorize("hasRole('TEACHER')")
    @Transactional
    @PutMapping(path = "/{id}")
    public ResponseEntity<Void> replace(@PathVariable long id, @RequestBody @Valid CourseDTO request) {
        CourseDTO updatedCourseDTO = courseMapper.toDTO(courseService.findByIdOrThrowBadRequestException(id));
        log.info(updatedCourseDTO.toString());
        if (updatedCourseDTO.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String teacherEmail = authentication.getName();

        if (!updatedCourseDTO.getTeacherDTO().getUserDTO().getEmail().equals(teacherEmail)) {
            throw new AccessDeniedException("You do not have permission to modify this course");
        }

        request.setId(id);
        Course course = courseMapper.toEntity(request);
        courseService.replace(course);
        log.info(course);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
