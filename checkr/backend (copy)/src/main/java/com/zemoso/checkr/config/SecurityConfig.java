package com.zemoso.checkr.config;

import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.zemoso.checkr.service.TokenDecodeService;
import com.zemoso.checkr.token.TokenFilter;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.spec.SecretKeySpec;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

    @Value("${jwt.key}")
    private String jwtKey;

    private TokenFilter tokenFilter;


    @Bean
    public UserDetailsService userDetailsService() {
        System.out.println("I am already done");
        return new InMemoryUserDetailsManager(
                User.withUsername("user")
                        .password("{noop}password")
                        .authorities("READ","ROLE_USER")
                        .build());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("hi I am here");

        return http
                .csrf(AbstractHttpConfigurer::disable)
                //.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
               // .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests( auth -> {
                            //   auth.requestMatchers("/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs/**", "/swagger-resources/**", "/webjars/**").anonymous();
                            //auth.requestMatchers("/api/auth/token").hasRole("USER");
                            auth.requestMatchers("/api/v1/*").authenticated();
                               auth.requestMatchers( "/api/auth/*",
                                    "/oauth/authorize",
                                    "/oauth/confirm_access",
                                    "/logout",
                                    "/oauth/revoke-token",
                                    "/swagger-ui.html",
                                    "/swagger-ui/**",
                                    "/v3/api-docs/**",
                                    "/error",
                                    "/swagger-resources/**",
                                    "/webjars/**"
                                    ).permitAll() ;// Allow access to Swagger UI resource

                        }
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS))
                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                .httpBasic(withDefaults())
                .addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("Before Security");
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(jwtKey.getBytes()));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] bytes = jwtKey.getBytes();
        SecretKeySpec originalKey = new SecretKeySpec(bytes, 0, bytes.length,"RSA");
        return NimbusJwtDecoder.withSecretKey(originalKey).macAlgorithm(MacAlgorithm.HS512).build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .build();
    }

}