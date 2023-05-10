package app.zinu.com.auth;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import app.zinu.com.config.JwtService;
import app.zinu.com.entity.Role;
import app.zinu.com.entity.User;
import app.zinu.com.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

	private final UserRepository repository;
	
	private final PasswordEncoder passwordEncoder;
	
	private final JwtService jwtService;
	
	private final AuthenticationManager authManager;
	
	public AuthenticationResponse register(RegisterRequest request) {
		
		var user = User.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.dob(request.getDob())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		repository.save(user);
		var jwtToken = jwtService.generateToken(user);
		
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.build();
	}

	public AuthenticationResponse authenticate(AuthenticationRequest request) {
		authManager.authenticate( new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		
		var user = repository.findByEmail(request.getEmail())
				.orElseThrow();
		
		var jwtToken = jwtService.generateToken(user);
		return AuthenticationResponse.builder()
				.token(jwtToken)
				.id(user.getUser_id())
				.build();
	}
}
