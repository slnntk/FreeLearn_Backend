package unifor.devweb.project.freelearn.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unifor.devweb.project.freelearn.domain.entities.Student;
import unifor.devweb.project.freelearn.dto.StudentDTO;
import unifor.devweb.project.freelearn.mapper.StudentMapper;
import unifor.devweb.project.freelearn.services.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@Log4j2
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    public ResponseEntity<Page<StudentDTO>> list(Pageable pageable) {
        Page<Student> students = studentService.listAll(pageable);
        Page<StudentDTO> studentDTOs = students.map(studentMapper::toDTO);
        return ResponseEntity.ok(studentDTOs);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<StudentDTO>> listAll() {
        List<Student> students = (List<Student>) studentService.listAllNonPageable();
        List<StudentDTO> studentDTOs = students.stream()
                .map(studentMapper::toDTO)
                .toList();
        return ResponseEntity.ok(studentDTOs);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentDTO> findById(@PathVariable long id) {
        Student student = studentService.findByIdOrThrowBadRequestException(id);
        StudentDTO studentDTO = studentMapper.toDTO(student);
        return ResponseEntity.ok(studentDTO);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<StudentDTO> save(@Valid @RequestBody StudentDTO request) {
        Student student = studentMapper.toEntity(request);
        Student savedStudent = studentService.save(student);
        StudentDTO savedStudentDTO = studentMapper.toDTO(savedStudent);
        return new ResponseEntity<>(savedStudentDTO, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Void> replace(@PathVariable long id, @RequestBody @Valid StudentDTO request) {
        Student existingStudent = studentService.findByIdOrThrowBadRequestException(id);
        if (existingStudent.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        Student updatedStudent = studentMapper.toEntity(request);
        updatedStudent.setId(id);
        studentService.replace(updatedStudent);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        studentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
