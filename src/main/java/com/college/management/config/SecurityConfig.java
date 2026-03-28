package com.college.management.config;
import com.college.management.security.CustomUserDetailsService;
import com.college.management.security.JwtAuthEntryPoint;
import com.college.management.security.JwtAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration @EnableWebSecurity @EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final JwtAuthFilter jwtAuthFilter;
    private final CustomUserDetailsService userDetailsService;
    private final JwtAuthEntryPoint unauthorizedHandler;
    public SecurityConfig(JwtAuthFilter jwtAuthFilter, CustomUserDetailsService userDetailsService, JwtAuthEntryPoint unauthorizedHandler) {
        this.jwtAuthFilter = jwtAuthFilter; this.userDetailsService = userDetailsService; this.unauthorizedHandler = unauthorizedHandler;
    }
    @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
            .exceptionHandling(ex -> ex.authenticationEntryPoint(unauthorizedHandler))
            .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // Public
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers("/actuator/health").permitAll()

                // Users - GET allowed for multiple roles, write only ADMIN
                .requestMatchers(HttpMethod.GET, "/api/users/**").hasAnyRole("ADMIN","HOD","FACULTY","DEAN","STUDENT")
                .requestMatchers(HttpMethod.POST, "/api/users/**").hasAnyRole("ADMIN", "HOD" , "FACULTY")
                .requestMatchers(HttpMethod.PUT, "/api/users/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/users/**").hasRole("ADMIN")

                // Departments - GET all roles, write only ADMIN
                .requestMatchers(HttpMethod.GET, "/api/departments/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/departments/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/departments/**").hasRole("ADMIN")

                // Courses - GET all roles, write only ADMIN
                .requestMatchers(HttpMethod.GET, "/api/courses/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/courses/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/courses/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/api/courses/**").hasRole("ADMIN")

                // Subjects - GET all roles, write ADMIN or HOD
                .requestMatchers(HttpMethod.GET, "/api/subjects/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/subjects/**").hasAnyRole("ADMIN","HOD")
                .requestMatchers(HttpMethod.PUT, "/api/subjects/**").hasAnyRole("ADMIN","HOD")

                // Attendance - GET all roles, write FACULTY or ADMIN
                .requestMatchers(HttpMethod.GET, "/api/attendance/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/attendance/**").hasAnyRole("FACULTY","ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/attendance/**").hasAnyRole("FACULTY","ADMIN")

                // Marks - GET all roles, write FACULTY or ADMIN
                .requestMatchers(HttpMethod.GET, "/api/marks/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/marks/**").hasAnyRole("FACULTY","ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/marks/**").hasAnyRole("FACULTY","ADMIN")

                // Assignments - GET all roles, write FACULTY or ADMIN
                .requestMatchers(HttpMethod.GET, "/api/assignments/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/assignments/**").hasAnyRole("FACULTY","ADMIN","STUDENT")
                .requestMatchers(HttpMethod.PUT, "/api/assignments/**").hasAnyRole("FACULTY","ADMIN")

                // Notifications
                .requestMatchers(HttpMethod.GET, "/api/notifications/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/api/notifications/**").hasAnyRole("ADMIN","DEAN","HOD","FACULTY")

                // Reports
                .requestMatchers("/api/reports/**").hasAnyRole("ADMIN","DEAN")

                // Everything else needs authentication
                .anyRequest().authenticated()
            )
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider p = new DaoAuthenticationProvider();
        p.setUserDetailsService(userDetailsService); p.setPasswordEncoder(passwordEncoder()); return p;
    }
    @Bean public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception { return config.getAuthenticationManager(); }
    @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(12); }
}
