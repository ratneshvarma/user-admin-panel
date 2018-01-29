package com.panel.controller;

import com.panel.entity.User;
import com.panel.response.TokenData;
import com.panel.security.JwtGenerator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/token")
public class TokenController {
	private JwtGenerator jwtGenerator;

	public TokenController(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}

	@PostMapping
	public TokenData generate(@RequestBody final User user) {
		System.out.println("TOKEN Hello");
		return jwtGenerator.generate(user);

	}
}
