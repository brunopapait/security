package br.com.papait.bruno.security.mapper.user;

import br.com.papait.bruno.security.commons.Mapper;
import br.com.papait.bruno.security.dto.UserDTO;
import br.com.papait.bruno.security.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Mapper<UserEntity, UserDTO> {
	@Override
	public UserEntity toPersistence(UserDTO objectDTO) {
		return new UserEntity(objectDTO.id(), objectDTO.login(), objectDTO.password());
	}

	@Override
	public UserDTO toDomain(UserEntity objectPersistence) {
		return new UserDTO(objectPersistence.getId(), objectPersistence.getUsername(), objectPersistence.getPassword());
	}
}
