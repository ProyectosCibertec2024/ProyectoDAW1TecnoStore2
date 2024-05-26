package pe.tecnostore.tecnostore.springsecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((auth) -> auth

                    .requestMatchers("/login"
                                ,"/logout"
                                ,"/resources/**"
                                ,"/static/**"
                                ,"/templates/**"
                                ,"/backoffice/**"
                                ,"/fragment/**"
                                ,"/css/**"
                                ,"/js/**"
                                ,"/img/**").permitAll()
                    .anyRequest().authenticated() )

                .formLogin(form -> form.loginPage("/login")
                        .defaultSuccessUrl("/dashboard")
                        .permitAll())

                .logout(log -> log
                        .logoutUrl("/login?logout")
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .permitAll());

        return http.build();
    }
}
