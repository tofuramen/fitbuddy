package com.fitBuddyGuy.fitBuddyApp.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@EnableWebSecurity // have to add or it can't find HttpSecurity type
@Configuration
public class SecurityConfig {

    private final SecurityContextHolderStrategy securityContextHolderStrategy =
            SecurityContextHolder.getContextHolderStrategy();

    private final SecurityContextRepository securityContextRepository =
            new HttpSessionSecurityContextRepository();


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {



        http
                .csrf(Customizer.withDefaults())
                .authorizeHttpRequests((requests) -> requests
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/", "/home", "/confirmation").permitAll()
                        .requestMatchers("/signup").permitAll()
                        .requestMatchers("/login").permitAll()
                        .requestMatchers("/css/**").permitAll()
                );
        http
                .authorizeHttpRequests((authorize -> authorize
                        .requestMatchers("/login_success")
                        .hasAuthority("USER").anyRequest().authenticated()


                ))

                .securityContext((securityContext) -> securityContext
                        .requireExplicitSave(true)

                )


                .formLogin(form ->
                        form
                                .loginPage("/login")
                                .usernameParameter("username")
                                .passwordParameter("password")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/login_success")
                                .permitAll()

                )
                .logout(l -> l
                        .logoutSuccessUrl("/").permitAll()


                );

        return http.build();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }





}
