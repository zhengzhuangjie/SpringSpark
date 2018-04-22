package com.example.domain

import javax.persistence._

import org.hibernate.validator.constraints.NotBlank

import scala.beans.BeanProperty
import scala.language.implicitConversions

@Table(name = "tmpuser")
@Entity
class User extends Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @BeanProperty
  var id: Long = _

  @BeanProperty
  @NotBlank
  var name: String = _

  override def toString = s"id=$id, name=$name"
}
