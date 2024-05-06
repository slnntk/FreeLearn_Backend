package unifor.devweb.project.freelearn.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import unifor.devweb.project.freelearn.domain.entities.user.User;
import unifor.devweb.project.freelearn.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "role", target = "role")
    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);
}