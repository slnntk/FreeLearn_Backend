package unifor.devweb.project.freelearn.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unifor.devweb.project.freelearn.config.CycleAvoidingMappingContext;
import unifor.devweb.project.freelearn.domain.entities.Teacher;
import unifor.devweb.project.freelearn.dto.TeacherDTO;
import unifor.devweb.project.freelearn.mapper.TeacherMapper;
import unifor.devweb.project.freelearn.services.TeacherService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("teachers")
@RequiredArgsConstructor
public class TeacherController {

    private final CycleAvoidingMappingContext context;
    private final TeacherService teacherService;
    private final TeacherMapper teacherMapper;

    @GetMapping
    public ResponseEntity<Page<TeacherDTO>> list(Pageable pageable) {
        Page<Teacher> teachers = teacherService.listAll(pageable);
        Page<TeacherDTO> teacherDTOs = teachers.map(teacherMapper::toDTO);
        return ResponseEntity.ok(teacherDTOs);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<TeacherDTO>> listAll() {
        List<Teacher> teachers = (List<Teacher>) teacherService.listAllNonPageable();
        List<TeacherDTO> teacherDTOs = teachers.stream()
                .map(teacherMapper::toDTO)
                .toList();
        return ResponseEntity.ok(teacherDTOs);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<TeacherDTO> findById(@PathVariable long id) {
        Teacher teacher = teacherService.findByIdOrThrowBadRequestException(id);
        TeacherDTO teacherDTO = teacherMapper.toDTO(teacher);
        return ResponseEntity.ok(teacherDTO);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<TeacherDTO> save(@Valid @RequestBody TeacherDTO request) {
        Teacher teacher = teacherMapper.toEntity(request, context);
        Teacher savedTeacher = teacherService.save(teacher);
        TeacherDTO savedTeacherDTO = teacherMapper.toDTO(savedTeacher);
        return new ResponseEntity<>(savedTeacherDTO, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Void> replace(@PathVariable long id, @RequestBody @Valid TeacherDTO request) {
        Teacher existingTeacher = teacherService.findByIdOrThrowBadRequestException(id);
        if (existingTeacher.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        Teacher updatedTeacher = teacherMapper.toEntity(request, context);
        updatedTeacher.setId(id);
        teacherService.replace(updatedTeacher);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        teacherService.delete(id);
        return ResponseEntity.noContent().build();
    }
}


