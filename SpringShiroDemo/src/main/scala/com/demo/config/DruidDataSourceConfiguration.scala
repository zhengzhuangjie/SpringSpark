package com.demo.config

import javax.sql.DataSource

import com.alibaba.druid.pool.DruidDataSource
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class DruidDataSourceConfiguration {
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource")
  def druidDataSource(): DataSource = {
    val druidDataSource: DruidDataSource = new DruidDataSource()
    druidDataSource
  }
}
