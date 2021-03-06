package com.example.oauth2

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

/**
  * https://vincentmi.gitbooks.io/spring-security-reference-zh/content/3.1_hello_web_security_java_configuration.html 
  */
@Configuration
@EnableWebSecurity
//@Primary
class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  /*@Autowired val clientDetailsService: ClientDetailsService = null

  override def configure(auth: AuthenticationManagerBuilder): Unit = {
    auth.inMemoryAuthentication()
      .withUser("user_1").password("123456").roles("USER")
      .and()
      .withUser("user_2").password("654321").roles("USER")
  }

  override def authenticationManagerBean(): AuthenticationManager = {
    def manager = super.authenticationManagerBean()

    manager
  }*/

  @Bean
  override def userDetailsService(): UserDetailsService = {
    val manager = new InMemoryUserDetailsManager()
    manager.createUser(User.withUsername("user_1").password("123456").authorities("USER").build());
    manager.createUser(User.withUsername("user_2").password("123456").authorities("USER").build());
    manager
  }

  //    @Bean
  //    @Override
  //    public AuthenticationManager authenticationManagerBean() throws Exception {
  //        AuthenticationManager manager = super.authenticationManagerBean();
  //        return manager;
  //    }

  override def configure(http: HttpSecurity) = {
    http
      .requestMatchers().anyRequest()
      .and()
      .authorizeRequests()
      .antMatchers("/oauth/*").permitAll();
  }
}
