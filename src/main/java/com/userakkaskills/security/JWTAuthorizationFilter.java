/**
 * 
 */
package com.userakkaskills.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

/**
 * @author Robert
 *
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
		response.addHeader("Access-Control-Allow-Headers", "Origin,Accept, X-Requested-With,Content-Type,Access-Control-Request-Method,Access-Control-Request-Headers,authorization");
		response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials,authorization");
		response.addHeader("Access-Control-Allow-Methods", "PUT,GET,POST,DELETE,PATCH");
		if(request.getMethod().equals("OPTIONS")){
			response.setStatus(HttpServletResponse.SC_OK);
		}
		else if(request.getRequestURI().equals("/login")){
			filterChain.doFilter(request, response);
			return;
		} 
		else {
		String jwt=request.getHeader(SecurityParam.HEADER_NAME);
		System.out.println("Token "+jwt);
		if(jwt==null || !jwt.startsWith(SecurityParam.PREFIX_HEADER)) {
			filterChain.doFilter(request, response);
			return;
		}
		JWTVerifier verifier=JWT.require(Algorithm.HMAC256(SecurityParam.SECRET)).build();
		String testjwt=jwt.substring(SecurityParam.PREFIX_HEADER.length());
		System.out.println("TEST JWT "+testjwt);
		DecodedJWT decodedJWT=verifier.verify(jwt.substring(SecurityParam.PREFIX_HEADER.length()));
		String username=decodedJWT.getSubject();
		List<String> roles=decodedJWT.getClaims().get("roles").asList(String.class);
		System.out.println("username= "+username);
		System.out.println("roles= "+roles);
		Collection<GrantedAuthority> authorities=new ArrayList<>();
		roles.forEach(auth->{
			authorities.add(new SimpleGrantedAuthority(auth));
		});
		UsernamePasswordAuthenticationToken token= new UsernamePasswordAuthenticationToken(username,null,authorities);
		SecurityContextHolder.getContext().setAuthentication(token);
		filterChain.doFilter(request, response);
		//JWT.decode(jwt.substring(SecurityParam.PREFIX_HEADER.length()));
		
	}
	}

}
