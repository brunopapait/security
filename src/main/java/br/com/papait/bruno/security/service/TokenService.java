package br.com.papait.bruno.security.service;

import br.com.papait.bruno.security.dto.LoginDTO;
import br.com.papait.bruno.security.entity.UserEntity;
import br.com.papait.bruno.security.mapper.user.UserMapper;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

	@Value("${jwt.key.token}")
	private String jwtKeyToken;

	private final UserMapper userMapper;

	public TokenService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	public LoginDTO generateToken(UserEntity userEntity) {
		var userDTO = this.userMapper.toDomain(userEntity);
		var token =  JWT.create()
						.withIssuer("Products")
						.withSubject(userDTO.login())
						.withClaim("id", userDTO.id())
						.withExpiresAt(LocalDateTime.now()
										.plusMinutes(30)
										.toInstant(ZoneOffset.of("-03:00")))
						.sign(Algorithm.HMAC256(jwtKeyToken));

		return new LoginDTO(userDTO.login(), null, token);
	}

	public String getSubject(String token) {
		return JWT.require(Algorithm.HMAC256(jwtKeyToken))
						.withIssuer("Products")
						.build()
						.verify(token)
						.getSubject();
	}
}
