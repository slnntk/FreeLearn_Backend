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
import unifor.devweb.project.freelearn.domain.entities.user.User;
import unifor.devweb.project.freelearn.dto.UserDTO;
import unifor.devweb.project.freelearn.mapper.UserMapper;
import unifor.devweb.project.freelearn.services.UserService;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<Page<UserDTO>> list(Pageable pageable) {
        Page<User> users;
        users = userService.listAll(pageable);
        Page<UserDTO> userDTOs = users.map(userMapper::toDTO);
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping(path = "/all")
    public ResponseEntity<List<UserDTO>> listAll() {
        List<User> users = (List<User>) userService.listAllNonPageable();
        List<UserDTO> userDTOs = users.stream()
                .map(userMapper::toDTO)
                .toList();
        return ResponseEntity.ok(userDTOs);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable long id) {
        User user = userService.findByIdOrThrowBadRequestException(id);
        UserDTO userDTO = userMapper.toDTO(user);
        return ResponseEntity.ok(userDTO);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<UserDTO> save(@Valid @RequestBody UserDTO request) {
        User user = userMapper.toEntity(request);
        User savedUser = userService.save(user);
        UserDTO savedUserDTO = userMapper.toDTO(savedUser);
        return new ResponseEntity<>(savedUserDTO, HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<Void> replace(@PathVariable long id, @RequestBody @Valid UserDTO request) {
        User existingUser = userService.findByIdOrThrowBadRequestException(id);
        if (existingUser.getId() == null) {
            return ResponseEntity.notFound().build();
        }

        User updatedUser = userMapper.toEntity(request);
        updatedUser.setId(id);
        userService.replace(updatedUser);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

