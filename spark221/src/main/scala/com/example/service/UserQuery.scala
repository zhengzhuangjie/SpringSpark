package com.example.service

import java.util.List

import com.example.dao.UserDao
import com.example.domain.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import scala.language.implicitConversions

@Service
class UserQuery @Autowired()(private val userDao: UserDao) {
  def findByName(name: String): List[User] = userDao.findByName(name)

  def findOne(id: java.lang.Long): User = userDao.findOne(id)
}
