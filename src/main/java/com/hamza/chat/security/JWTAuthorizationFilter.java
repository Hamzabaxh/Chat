package com.hamza.chat.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@WebFilter("/your-protected-endpoint")
public class JWTAuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic here (if needed).
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        if (request instanceof HttpServletRequest) {
            String jwt = ((HttpServletRequest) request).getHeader("Authorization");

            if (jwt == null || !jwt.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SecParams.SECRET)).build();
            // Remove the "Bearer" prefix from the JWT
            jwt = jwt.substring(7); // 7 characters in "Bearer "

            DecodedJWT decodedJWT = verifier.verify(jwt);
            String username = decodedJWT.getSubject();
            List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);

            Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            for (String r : roles)
                authorities.add(new SimpleGrantedAuthority(r));

            UsernamePasswordAuthenticationToken user =
                    new UsernamePasswordAuthenticationToken(username, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(user);
        }

        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup resources (if needed).
    }
}
