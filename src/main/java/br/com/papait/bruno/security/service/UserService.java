package br.com.papait.bruno.security.service;

import br.com.papait.bruno.security.dto.UserDTO;
import br.com.papait.bruno.security.mapper.user.UserMapper;
import br.com.papait.bruno.security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final UserMapper userMapper;

	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
	}

	public UserDTO createUSer(UserDTO userDTO) {
		var userPersistence = userMapper.toPersistence(userDTO);
		userPersistence.setPassword(passwordEncoder.encode(userPersistence.getPassword()));
		var user = userRepository.save(userPersistence);
		return userMapper.toDomain(user);
	}

	public List<UserDTO> findAll() {
		var users = userRepository.findAll();
		return users
						.stream()
						.peek(user -> user.setPassword(null))
						.map(userMapper::toDomain)
						.toList();
	}
}
