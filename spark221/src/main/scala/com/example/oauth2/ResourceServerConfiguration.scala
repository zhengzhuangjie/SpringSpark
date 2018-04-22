package com.example.oauth2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.config.annotation.web.configuration.{EnableResourceServer, ResourceServerConfigurerAdapter}
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer

/**
  * 资源服务器
  */
@Configuration
@EnableResourceServer
class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {
  override def configure(resources: ResourceServerSecurityConfigurer): Unit = {
    resources.resourceId(OAuth2ServerConfig.DEMO_RESOURCE_ID).stateless(true)
  }

  override def configure(http: HttpSecurity): Unit = {
    //    http.authorizeRequests()
    //      .antMatchers("/product/**", "signup", "/about").permitAll()
    //      .antMatchers("/order/**").hasRole("USER").anyRequest().authenticated()
    //          .antMatchers("/order/**").access("hasRole(USER) AND hasRole()")
    http.sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
      .and().requestMatchers().anyRequest()
      .and().anonymous()
      .and().authorizeRequests()
      .antMatchers("/product/**").access("#oauth2.hasScope('select') and hasRole('ROLE_USER')")
      .antMatchers("/order/**").authenticated()
  }
}
