package com.panel.security;

import com.panel.dto.UserDto;
import com.panel.dto.UserDetail;
import com.panel.dto.JwtAuthenticationTokenDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static com.panel.constant.SecurityConstants.INVALID_TOKEN_ERROR;

import java.util.List;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	@Autowired
	private JwtValidator validator;

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

	}

	@Override
	protected UserDetails retrieveUser(String username,
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
		JwtAuthenticationTokenDto jwtAuthenticationToken = (JwtAuthenticationTokenDto) usernamePasswordAuthenticationToken;
		String token = jwtAuthenticationToken.getToken();
		UserDto userDto = validator.validate(token);
		if (userDto == null) {
			throw new RuntimeException(INVALID_TOKEN_ERROR);
		}

		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
				.commaSeparatedStringToAuthorityList(userDto.getUserRole());
		return new UserDetail(userDto.getFirstName(), userDto.getUserId(), token, grantedAuthorities);
	}

	@Override
	public boolean supports(Class<?> aClass) {
		return (JwtAuthenticationTokenDto.class.isAssignableFrom(aClass));
	}
}
