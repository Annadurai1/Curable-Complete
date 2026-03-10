package com.curable.util;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.curable.exception.InvalidJwtAuthenticationException;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 */
@Component
public class JwtTokenUtil implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

	@Value("${security.jwt.token.secret-key:secret}")
	private String secretKey = "secret";

	@Value("${security.jwt.token.expire-length:3600000}")
	private long validityInMilliseconds = 90l * (24l * 3600000l);

	/**
	 * 
	 * @param token
	 * @return
	 */
	public boolean validateToken(String token) {
		try {
			Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

			if (claims.getBody().getExpiration().before(new Date())) {
				return false;
			}
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			throw new InvalidJwtAuthenticationException("Expired or invalid JWT token");
		}
	}

	/**
	 * 
	 * @param authToken
	 * @return
	 */
	public String getUsernameFromToken(String authToken) {
		logger.info("User name ::" + authToken);
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(authToken).getBody().getSubject();
	}

	/**
	 * 
	 * @param username
	 * @param roles
	 * @return
	 */
	public String createToken(String userName, String password, List<String> roles) {
		Claims claims = Jwts.claims().setSubject(userName);
		claims.put("userName", userName);
		claims.put("roles", roles);
		claims.put("password", password);
		Date now = new Date();
		Date validity = new Date(now.getTime() + 600000000);

		return Jwts.builder()//
				.setClaims(claims)//
				.setIssuedAt(now)//
				.setExpiration(validity)//
				.signWith(SignatureAlgorithm.HS512, secretKey)//
				.compact();
	}

}
