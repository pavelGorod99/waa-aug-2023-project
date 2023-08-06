package com.example.Alumni_Management_Portal.security;

import com.example.Alumni_Management_Portal.services.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    private final org.springframework.security.core.userdetails.UserDetailsService UserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String userEmail;

        String jwt = extractTokenFromRequest(request);
        if (jwt == null) {
            filterChain.doFilter(request, response);
            return;
        }

        try
        {
            userEmail = jwtService.extractUserName(jwt);
            if (userEmail == null || SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = this.UserDetailsService.loadUserByUsername(userEmail);

                if (jwtService.isTokenValid(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
                else{
                    System.out.println("error");
                }
            }
            filterChain.doFilter(request, response);
        }catch (ExpiredJwtException e){
//            response.setStatus(HttpStatus.UNAUTHORIZED.value());
//            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid JWT token");
        }catch (Exception e){
//            System.out.println("error" + e);

//            response.sendError(HttpStatus.UNAUTHORIZED.value(), "Invalid JWT token");
        }


    }


    public String extractTokenFromRequest(HttpServletRequest request) {

        final String authorizationHeader = request.getHeader("Authorization");
        System.out.println("authorizationHeader =" +authorizationHeader);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            var token = authorizationHeader.substring(7);
            return token;
        }
        return null;
    }

}
