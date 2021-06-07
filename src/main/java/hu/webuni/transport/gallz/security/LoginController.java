package hu.webuni.transport.gallz.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import hu.webuni.transport.gallz.dto.LoginDto;

@RestController
public class LoginController {
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtService jwtService;
	
	@PostMapping("/api/login")
	public String login(@RequestBody @Valid LoginDto loginDto) {
		Authentication auth = authManager.authenticate(	new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));		
		return jwtService.createJwtToken((UserDetails)auth.getPrincipal());
	}
}
