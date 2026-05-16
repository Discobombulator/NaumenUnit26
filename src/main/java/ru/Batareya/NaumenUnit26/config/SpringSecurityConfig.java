package ru.Batareya.NaumenUnit26.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import ru.Batareya.NaumenUnit26.service.UserService;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig{

    private final UserService userService;

    public SpringSecurityConfig(UserService userService) {
        this.userService = userService;
    }

    /**
     * Настраивает цепочку фильтров безопасности
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth

                        .requestMatchers("/login","/logo.png", "/static/**")
                        .permitAll()

                        .requestMatchers(
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).hasRole("ADMIN")

                        .requestMatchers("/api/**").authenticated()

                        .anyRequest().authenticated()
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .defaultSuccessUrl("/", true)
                        .failureHandler((request, response, exception) -> {
                            response.sendRedirect("/login?error=true");
                        })
                        .permitAll()
                        .permitAll()
                )

                .logout(logout -> logout
                        .logoutSuccessUrl("/login")
                        .permitAll()
                );

        return http.build();
    }


}