package edu.festu.ivankuznetsov.securitydemo.config;

import edu.festu.ivankuznetsov.securitydemo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    private final AccountService userDetailsService;
    private final JwtAuthFilter authFilter;

    @Autowired
    public WebSecurityConfig(AccountService userDetailsService, JwtAuthFilter authFilter) {
        this.userDetailsService = userDetailsService;
        this.authFilter = authFilter;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService((UserDetailsService) userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/lc", true)
                        .permitAll()
                )
                .cors().disable()
                .csrf().disable()
                .httpBasic(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults())
                .rememberMe(rememberMe -> rememberMe.tokenValiditySeconds(86400))
                .authorizeRequests(authorizeRequests -> {
                    authorizeRequests
                            .antMatchers(HttpMethod.POST,"/add")
                            .permitAll()
                            .antMatchers(HttpMethod.DELETE,"/delete").hasAnyRole("ROLE_ADMIN", "ROLE_USER")
                            .antMatchers(HttpMethod.PATCH,"/edit").hasAnyRole("ROLE_ADMIN", "ROLE_USER")
                            .antMatchers(HttpMethod.GET,"/","/login**","/logout**")
                            .permitAll()
                            .antMatchers(HttpMethod.GET,"/users/**").hasAnyRole("ROLE_USER, ROLE_ADMIN");
                });

        return http.build();
    }


    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

