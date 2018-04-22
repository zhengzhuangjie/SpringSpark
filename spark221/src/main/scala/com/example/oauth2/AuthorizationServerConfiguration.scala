package com.example.oauth2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.{AuthorizationServerConfigurerAdapter, EnableAuthorizationServer, EnableResourceServer}
import org.springframework.security.oauth2.config.annotation.web.configurers.{AuthorizationServerEndpointsConfigurer, AuthorizationServerSecurityConfigurer}
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore

/**
  * 授权服务器
  */
@Configuration
@EnableAuthorizationServer
class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {
  @Autowired val authenticationManager: AuthenticationManager = null

  @Autowired val redisConnectionFactory: RedisConnectionFactory = null

  override def configure(clients: ClientDetailsServiceConfigurer): Unit = {
    val clientBuilder = clients.inMemory().withClient("client_1").resourceIds(OAuth2ServerConfig.DEMO_RESOURCE_ID)
    clientBuilder.authorizedGrantTypes("client_credentials", "refresh_token")
    clientBuilder.scopes("select")
    clientBuilder.authorities("client")
    clientBuilder.secret("123456")
    val passwordBuilder = clientBuilder.and().withClient("client_2").resourceIds(OAuth2ServerConfig.DEMO_RESOURCE_ID)
    passwordBuilder.authorizedGrantTypes("password", "refresh_token")
    passwordBuilder.scopes("select")
    passwordBuilder.authorities("client")
    passwordBuilder.secret("123456")
  }

  override def configure(endpoints: AuthorizationServerEndpointsConfigurer): Unit = {
    endpoints.tokenStore(tokenStore)
      .authenticationManager(authenticationManager)
      .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
  }

  override def configure(security: AuthorizationServerSecurityConfigurer): Unit = {
    security.allowFormAuthenticationForClients()
  }

  @Bean
  def tokenStore: TokenStore = {
    val redisTokenStore = new RedisTokenStore(redisConnectionFactory)
    redisTokenStore
  }
}
