package com.app.securityservice.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");
		if(header!=null && header.startsWith("Bearer ")) {
			String token = header.substring(7);
			if(jwtUtils.validateToken(token)) {
				String tokenUsername = jwtUtils.getTokenUsername(token);
				if(tokenUsername!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
					UserDetails user = userDetailsService.loadUserByUsername(tokenUsername);
					UsernamePasswordAuthenticationToken authDtls = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
					SecurityContextHolder.getContext().setAuthentication(authDtls);
				}else {
					System.out.println("Username is NULL or authentication already set, "+tokenUsername);
				}
			}
		}else {
			System.out.println("Request Header does not contain a Bearer token");
		}
		filterChain.doFilter(request, response);
		
	}

}
