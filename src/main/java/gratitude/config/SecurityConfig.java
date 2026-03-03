package gratitude.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // CRUD用
//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .anyRequest().permitAll()
//                )
//                .csrf(csrf -> csrf.disable());
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ログインページ確認用
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
       http
               .authorizeHttpRequests(auth -> auth
                       .requestMatchers(
                               "/account/login",
                               "/account/sign-up",
                               "/gratitude",
                               "/error",
                               "/css/**",
                               "/js/**"
                       ).permitAll()
                       .anyRequest().authenticated()
               )
               .formLogin(form -> form
                       .loginPage("/account/login")
                       .loginProcessingUrl("/account/login")
                       .usernameParameter("email")
                       .passwordParameter("password")
                       .defaultSuccessUrl("/gratitude/list", true)
                       .failureUrl("/account/login?error")
                       .permitAll()
               )
               .logout(logout -> logout
                       .logoutSuccessUrl("/account/login?logout")
                       .invalidateHttpSession(true)
                       .deleteCookies("JSESSIONID")
               );

       return http.build();
    }


//    @Bean
//    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/login", "/signup", "/email/verify", "/css/**", "/js/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .usernameParameter("email") // form nameとの同一
//                        .passwordParameter("password")
//                        .defaultSuccessUrl("/", true)
//                        .failureUrl("/login?error")
//                )
//                .logout(logout -> logout.logoutSuccessUrl("/login?logout"));
//        return http.build();
//    }
//
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
