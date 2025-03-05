package com.example.bookstore.service

import com.example.bookstore.domain.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl @Autowired constructor(
    private val userRepository: UserRepository
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(username: String): UserDetails {
        println("Attempting to load user: $username") // Debug statement
        val user = userRepository.findByUsername(username)
        
        if (user == null) {
            println("User not found: $username") // Debug statement
            throw UsernameNotFoundException("User not found with username: $username")
        }
        
        println("User found: ${user.username}, Role: ${user.role}") // Debug statement
        
        return User.builder()
            .username(user.username)
            .password(user.passwordHash)
            .authorities(SimpleGrantedAuthority("ROLE_" + user.role))
            .build()
    }
}