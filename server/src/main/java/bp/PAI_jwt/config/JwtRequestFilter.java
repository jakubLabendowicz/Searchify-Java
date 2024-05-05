package bp.PAI_jwt.config;

import bp.PAI_jwt.service.JwtUserDetailsService;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(jwtToken);
            } catch (IllegalArgumentException e) {
                //Tydzień 9, 6. Dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
                throw new ServletException("Unable to get JWT Token", e);
            } catch (ExpiredJwtException e) {
                //Tydzień 9, 6. Dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
                throw new ServletException("JWT Token has expired", e);
            }
        } else {
            //Tydzień 9, 6. Dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
            throw new ServletException("JWT Token does not begin with Bearer String");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            authenticateUser(request, jwtToken, username);
        }
        chain.doFilter(request, response);
    }

    //Tydzień 9, 1. Znaczące (jasne i zrozumiałe) nazwy do klas, metod i zmiennych, znaczące w całym programie to samo (bez synonimów)
    private void authenticateUser(HttpServletRequest request, String jwtToken, String username) throws ServletException {
        UserDetails userDetails = getUserDetails(username, jwtToken);

        if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
            configureSpringSecurityAuthentication(request, userDetails);
        }
    }

    //Tydzień 9, 1. Znaczące (jasne i zrozumiałe) nazwy do klas, metod i zmiennych, znaczące w całym programie to samo (bez synonimów)
    private UserDetails getUserDetails(String username, String jwtToken) throws ServletException {
        UserDetails userDetails;
        try {
            userDetails = this.jwtUserDetailsService.loadUserByUsername(username);
        } catch (Exception e) {
            //Tydzień 9, 6. Dodaj zwracanie wyjątków zamiast kodów błędów (3 wystąpienia)
            throw new ServletException("User details not found for username: " + username, e);
        }
        return userDetails;
    }

    //Tydzień 9, 1. Znaczące (jasne i zrozumiałe) nazwy do klas, metod i zmiennych, znaczące w całym programie to samo (bez synonimów)
    private void configureSpringSecurityAuthentication(HttpServletRequest request, UserDetails userDetails) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
        usernamePasswordAuthenticationToken
                .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }
}
