package com.example.dao

import java.util.List

import com.example.domain.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository

import scala.language.implicitConversions

trait UserDao extends CrudRepository[User, java.lang.Long] {
  @Query(value = "select id, name from tmpuser where name=?1", nativeQuery = true)
  def findByName(name: String): List[User]
}