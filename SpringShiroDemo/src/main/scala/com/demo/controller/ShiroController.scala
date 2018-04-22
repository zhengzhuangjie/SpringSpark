package com.demo.controller

import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

@RestController
//@RequestMapping(Array("/demo"))
class ShiroController {
  @RequestMapping(value = Array("/home"), method = Array(RequestMethod.GET))
  def home(): String = {
    "Hello World!"
  }
}
