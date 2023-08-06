package com.example.Alumni_Management_Portal.config;
import com.example.Alumni_Management_Portal.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((request) ->
                        {
                            request.requestMatchers("/events/**").hasAnyAuthority("STUDENT", "ADMIN");
                            request.requestMatchers("/insights/**").hasAnyAuthority("STUDENT", "ADMIN");
                            request.requestMatchers("/contacts/**").hasAnyAuthority("STUDENT", "ADMIN");
                            request.requestMatchers("/jobs/**").hasAnyAuthority("STUDENT", "ADMIN");
                            request.requestMatchers("/posts/**").hasAnyAuthority("STUDENT", "ADMIN");
                            request.requestMatchers("/profiles/**").hasAnyAuthority("STUDENT", "ADMIN");
                            request.requestMatchers("/users").hasAnyAuthority("STUDENT", "ADMIN");
                            request.anyRequest().permitAll();
                        }
                )
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
