package com.panel.security;

import com.panel.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import static com.panel.constant.SecurityConstants.SECRET_KEY;

import org.springframework.stereotype.Component;

@Component
public class JwtValidator {
	public UserDto validate(String token) {
		UserDto adminUser = null;
		try {
			Claims body = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();

			adminUser = new UserDto();
		//	adminUser.setFirstName(body.getSubject());
			adminUser.setFirstName((String)body.get("firstName"));
//			System.out.println("Only Body========="+body.toString());
//			System.out.println("Body==========="+body.getSubject()+"====="+(String)body.get("userRole")+"======"+(String)body.get("firstName")
//			+"====="+Long.parseLong((String) body.get("userId")));
			adminUser.setUserId(Long.parseLong((String) body.get("userId")));
			adminUser.setUserRole((String) body.get("userRole"));
		} catch (Exception e) {
			System.out.println(e);
		}
		return adminUser;
	}
}
