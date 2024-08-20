package hnqd.project.ApartmentManagement.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable());
//                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authorizeHttpRequests(authorize -> {
//                    authorize
//                            .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
//                            .requestMatchers("/api/v1/auth/**", "/api/v1/users/**", "/api/v1/posts/**",
//                                    "/api/v1/comments/{postId}/**", "/api/v1/refreshToken/**", "/ws/**",
//                                    "/api/v1/cloudinary/upload").permitAll()
//                            .requestMatchers(
//                                    "/api/v1/posts/save",
//                                    "/api/v1/posts/delete/{postId}",
//                                    "/api/v1/posts/update/{postId}",
//                                    "/api/v1/posts/update/{postId}/like",
//                                    "/api/v1/cloudinary/uploadMultipleFiles",
//                                    "/api/v1/comments/save/**",
//                                    "/api/v1/comments/{postId}/delete/{commentId}",
//                                    "/api/v1/comments/{postId}/update/{commentId}",
//                                    "/api/v1/rooms/**").authenticated()
//                            .anyRequest().authenticated();
//                    ;
//                })
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
