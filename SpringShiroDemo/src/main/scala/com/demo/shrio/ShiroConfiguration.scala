package com.demo.shrio

import java.util.LinkedHashMap

import org.apache.shiro.spring.web.ShiroFilterFactoryBean
import org.springframework.context.annotation.{Bean, Configuration}
import org.apache.shiro.mgt.SecurityManager
import org.apache.shiro.web.mgt.DefaultWebSecurityManager

/**
  * shiro配置类
  * 1、需要配置ShiroFilterFactory
  * 2、配置SecurityManager
  */
@Configuration
class ShiroConfiguration {
  /**
    * 1、定义ShrioFactoryBean
    * 2、设置SecurityManager
    * 3、配置拦截器
    * 4、返回ShiroFactoryBean
    *
    * @param securityManager
    * @return
    */
  @Bean
  def shiroFilter(securityManager: SecurityManager): ShiroFilterFactoryBean = {
    val shiroFilterFactoryBean: ShiroFilterFactoryBean = new ShiroFilterFactoryBean()
    shiroFilterFactoryBean.setSecurityManager(securityManager)
    //    配置拦截器，shrio会根据添加顺序进行拦截
    val filterChainMap = new LinkedHashMap[String, String]
    //    配置退出
    filterChainMap.put("/logout", "logout")
    //    所有url都必须认证通过才可以访问
    filterChainMap.put("/**", "authc")
    //    设置登录url
    shiroFilterFactoryBean.setLoginUrl("/login")
    //    设置成功之后要跳转的连接
    shiroFilterFactoryBean.setSuccessUrl("/index")
    //    设置未授权界面
    shiroFilterFactoryBean.setUnauthorizedUrl("/403")
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainMap)
    shiroFilterFactoryBean
  }

  @Bean
  def securityManager(): SecurityManager = {
    val securityManager: DefaultWebSecurityManager = new DefaultWebSecurityManager()
    securityManager
  }
}
