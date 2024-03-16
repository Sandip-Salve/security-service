package com.app.securityservice.config;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.app.securityservice.model.SecuredUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	@Autowired
	private Environment env;
	
	public String generateToken(Authentication auth) {
		SecuredUser user = (SecuredUser)auth.getPrincipal();
		String secret = env.getProperty("jwt.token.secret");
		String expTimeStr = env.getProperty("jwt.token.expiration.millis");
		int expTime = Integer.parseInt(expTimeStr);
		return Jwts.builder().setSubject(user.getUsername()).setIssuedAt(new Date()).setExpiration(new Date((new Date()).getTime()+expTime)).signWith(SignatureAlgorithm.HS256, secret).compact();
	}
	
	public String getTokenUsername(String token) {
		String secret = env.getProperty("jwt.token.secret");
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}
	
	public boolean validateToken(String token) {
		String secret = env.getProperty("jwt.token.secret");
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		}catch(Exception ex) {
			System.out.println("Invalid Token : "+ex.getMessage());
		}
		return false;
	}
}
