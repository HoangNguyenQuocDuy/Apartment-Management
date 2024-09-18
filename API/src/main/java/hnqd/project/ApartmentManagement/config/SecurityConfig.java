package hnqd.project.ApartmentManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(authorize -> {
                    // ROLE_CUSTOMER access rules
                    authorize.requestMatchers(HttpMethod.POST, "/api/relatives/").hasRole("RESIDENT");
                    authorize.requestMatchers(HttpMethod.DELETE, "/api/relatives/**").hasRole("RESIDENT");
                    authorize.requestMatchers(HttpMethod.POST, "/api/feedbacks/**").hasRole("RESIDENT");
                    authorize.requestMatchers(HttpMethod.PUT, "/api/feedbacks/**").hasRole("RESIDENT");
                    authorize.requestMatchers(HttpMethod.DELETE, "/api/feedbacks/**").hasRole("RESIDENT");
                    authorize.requestMatchers(HttpMethod.POST, "/api/visitors/**").hasRole("RESIDENT");
                    authorize.requestMatchers(HttpMethod.PUT, "/api/visitors/**").hasRole("RESIDENT");

                    // ROLE_CUSTOMER or ROLE_ADMIN access rules
                    authorize.requestMatchers(HttpMethod.GET, "/api/parkingRights/**").hasAnyRole("RESIDENT", "ADMIN");
                    authorize.requestMatchers(HttpMethod.POST, "/api/parkingRights/**").hasAnyRole("RESIDENT", "ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT, "/api/parkingRights/**").hasAnyRole("RESIDENT", "ADMIN");
                    authorize.requestMatchers(HttpMethod.GET, "/api/entryRights/**").hasAnyRole("RESIDENT", "ADMIN");
                    authorize.requestMatchers(HttpMethod.POST, "/api/entryRights/**").hasAnyRole("RESIDENT", "ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT, "/api/entryRights/**").hasAnyRole("RESIDENT", "ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT, "/api/visitLogs/**").hasAnyRole("RESIDENT", "ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT, "/api/users/**").hasAnyRole("RESIDENT", "ADMIN");
                    authorize.requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("RESIDENT", "ADMIN");

                    // ROLE_ADMIN access rules
                    authorize.requestMatchers(HttpMethod.DELETE, "/api/rooms/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.POST, "/api/orders/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.PUT, "/api/orders/**").hasRole("ADMIN");
                    authorize.requestMatchers(HttpMethod.POST, "/api/users/**").hasRole("ADMIN");
//                    authorize.requestMatchers("/orders/**", "/rooms/**", "/lockers/**", "/invoices/**",
//                            "/api/entryRights/", "/users/", "/parkingRights").hasRole("ADMIN");
//                    authorize.requestMatchers(HttpMethod.POST, "/api/users/block/**", "/api/lockers/**").hasRole("ADMIN");
                    // Permit specific public endpoints
                    authorize.requestMatchers("/api/auth/login", "/api/auth/forgotPassword",
                            "/api/auth/resetPassword", "/ws/**").permitAll();

                    // All other requests need to be authenticated
                    authorize.anyRequest().authenticated();
                })
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:3000"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
