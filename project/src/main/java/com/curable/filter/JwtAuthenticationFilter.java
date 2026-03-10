package com.curable.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.curable.util.Constant;
import com.curable.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;

/**
 * 
 * @author Annadurai S
 *
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		String header = req.getHeader(Constant.HEADER_STRING);
		String username = null;
		String authToken = null;
		if (header != null && header.startsWith(Constant.TOKEN_PREFIX)) {
			authToken = header.replace(Constant.TOKEN_PREFIX, "");
			try {
				username = jwtTokenUtil.getUsernameFromToken(authToken);
				logger.info("username " + username);
			} catch (IllegalArgumentException e) {
				logger.error("An error occured during getting username from token", e);
			} catch (ExpiredJwtException e) {
				logger.warn("Token is expired and not valid anymore", e);
			} catch (SignatureException e) {
				logger.error("Authentication Failed. Username or Password not valid.");
			}

		}
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			if (jwtTokenUtil.validateToken(authToken)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username,
						authToken, null);
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		chain.doFilter(req, res);
	}

}
