package com.example.bookstore

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authorize ->
                authorize
                    .requestMatchers("/login", "/css/**", "/js/**").permitAll() // Replaced antMatchers
                    .anyRequest().authenticated()
            }
            .formLogin { formLogin ->
                formLogin
                    .loginPage("/login")
                    .defaultSuccessUrl("/booklist", true)
                    .permitAll()
            }
            .logout { logout ->
                logout.permitAll()
            }
        return http.build()
    }

    @Bean
    fun userDetailsService(): UserDetailsService {
        val user: UserDetails = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build()

        val admin: UserDetails = User.withDefaultPasswordEncoder()
            .username("admin")
            .password("password")
            .roles("USER", "ADMIN")
            .build()

        return InMemoryUserDetailsManager(user, admin)
    }
}
