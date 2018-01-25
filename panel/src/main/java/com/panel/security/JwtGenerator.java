package com.panel.security;

import com.panel.dto.UserDto;
import com.panel.entity.User;
import com.panel.response.TokenData;
import com.panel.service.UserServiceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.panel.constant.SecurityConstants.EXPIRATION_TIME;
import static com.panel.constant.SecurityConstants.SECRET_KEY;

import java.util.Date;

@Component
public class JwtGenerator {
	@Autowired
	private UserServiceImpl userService;

	public TokenData generate(User user) {
		Claims claims = Jwts.claims().setSubject(user.getFirstName());
		claims.put("userId", String.valueOf(user.getUserId()));
		claims.put("userRole", user.getUserRole());
//		System.out.println("Findd========" + user.getUserRole() + "" + "======" + String.valueOf(user.getUserId())
//				+ "=====" + user.getFirstName() + "=====" + "=====" + user.getLastName());
		User userEntity = userService.findUserById(user.getUserId());
		UserDto userDto = userService.userDisassembler(userEntity);
//		System.out.println("Entity Id========" + userService.findUserById(user.getUserId()));
		if (userDto != null && userDto.getUserRole().equals("admin")) {
			System.out.println("role======" + userDto.getUserRole());
			String token = Jwts.builder().setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
					.setClaims(claims).signWith(SignatureAlgorithm.HS512, SECRET_KEY).compact();

			return new TokenData(200, new Date(), token);
		} else
			return new TokenData(401, new Date(), "User is not authorized to access the Resource");
	}
}
