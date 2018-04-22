package com.example.controller

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.{PathVariable, RequestMapping, RequestMethod, RestController}

@RestController
@RequestMapping(Array("/"))
class OAuth2Controller {
  @RequestMapping(value = Array("/product/{id}"), method = Array(RequestMethod.GET))
  def getProduct(@PathVariable id: String): String = {
    val authentication = SecurityContextHolder.getContext.getAuthentication
    "product id=" + id
  }

  @RequestMapping(value = Array("/order/{id}"), method = Array(RequestMethod.GET))
  def getOrder(@PathVariable id: String): String = {
    val authentication = SecurityContextHolder.getContext.getAuthentication
    "order id=" + id
  }
}
