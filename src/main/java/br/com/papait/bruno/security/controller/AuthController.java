package br.com.papait.bruno.security.controller;

import br.com.papait.bruno.security.dto.LoginDTO;
import br.com.papait.bruno.security.entity.UserEntity;
import br.com.papait.bruno.security.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

	private final AuthenticationManager authenticationManager;

	private final TokenService tokenService;

	public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
		this.authenticationManager = authenticationManager;
		this.tokenService = tokenService;
	}

	@PostMapping("/login")
	public LoginDTO login(@RequestBody LoginDTO login) {
		var usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(login.login(), login.password());

		var authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		var user = (UserEntity) authenticate.getPrincipal();
		return this.tokenService.generateToken(user);

	}
}
