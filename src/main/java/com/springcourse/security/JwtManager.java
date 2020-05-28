package com.springcourse.security;

import org.springframework.stereotype.Component;

import com.springcourse.constant.SecurityConstants;
import com.springcourse.dto.UserLoginResponsedto;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Calendar;
import java.util.List;

@Component
public class JwtManager {

	public UserLoginResponsedto createToken(String email, List<String> roles) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, SecurityConstants.JWT_EXP_DAYS);
		
		String jwt = Jwts.builder()
						 .setSubject(email)
						 .setExpiration(calendar.getTime())
						 .claim(SecurityConstants.JWT_ROLE_KEY, roles)
						 .signWith(SignatureAlgorithm.HS512, SecurityConstants.API_KEY.getBytes())
						 .compact();
		
		Long expireIn = calendar.getTimeInMillis();
		
		return new UserLoginResponsedto(jwt, expireIn, SecurityConstants.JWT_PROVIDER);
	}
	
	public Claims parseToken(String jwt) throws JwtException {
		Claims claims = Jwts.parser()
							.setSigningKey(SecurityConstants.API_KEY.getBytes())
							.parseClaimsJws(jwt)
							.getBody();
		
		
		return claims;
	}
}
