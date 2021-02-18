package com.pk.petrolstationmonolith.config;

import com.pk.petrolstationmonolith.auth.JwtAuthenticationEntryPoint;
import com.pk.petrolstationmonolith.auth.JwtTokenAuthenticationFilter;
import com.pk.petrolstationmonolith.auth.JwtUsernameAndPasswordAuthenticationFilter;
import com.pk.petrolstationmonolith.auth.UserDetailsServiceImpl;
import com.pk.petrolstationmonolith.enums.account.Roles;
import com.pk.petrolstationmonolith.properties.auth.JwtProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.security.Key;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsServiceImpl userDetailsService;
    private final JwtProperties jwtProperties;
    private final Key jwtSecret;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, JwtProperties jwtProperties, Key jwtSecret) {
        this.userDetailsService = userDetailsService;
        this.jwtProperties = jwtProperties;
        this.jwtSecret = jwtSecret;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()

                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .exceptionHandling().authenticationEntryPoint(new JwtAuthenticationEntryPoint())

                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtProperties, jwtSecret))
                .addFilterAfter(new JwtTokenAuthenticationFilter(jwtSecret), UsernamePasswordAuthenticationFilter.class)

                .authorizeRequests()

                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()

                .antMatchers("/actuator/**").hasRole(Roles.ADMIN.name())

                .antMatchers(HttpMethod.POST, "/account/login").permitAll()
                .antMatchers(HttpMethod.POST, "/account/password").permitAll()
                .antMatchers(HttpMethod.POST, "/account/companies").permitAll()
                .antMatchers(HttpMethod.POST, "/account/employees").permitAll()
                .antMatchers(HttpMethod.POST, "/account/individuals").permitAll()
                .antMatchers(HttpMethod.DELETE, "/account/password").permitAll()

                .antMatchers(HttpMethod.GET, "/pricelist").permitAll()
                .antMatchers(HttpMethod.PUT, "/pricelist").hasRole(Roles.ADMIN.name())

                .antMatchers(HttpMethod.POST, "/carwash/reservations").permitAll()

                .antMatchers("/monitoring/parameters").hasRole(Roles.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/monitoring").hasRole(Roles.ADMIN.name())

                .anyRequest().authenticated()

                .and()
                .headers().frameOptions().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
