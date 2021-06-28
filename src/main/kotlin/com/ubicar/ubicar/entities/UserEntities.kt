package com.ubicar.ubicar.entities

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonManagedReference
import org.hibernate.annotations.CreationTimestamp
import java.time.LocalDate
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany
import javax.persistence.ManyToOne
import javax.persistence.Table

@Table(name = "user_data")
@Entity
class User(
  var userName: String,

  @Column(unique = true)
  var email: String,

  var password: String? = null,

  @Enumerated(value = EnumType.STRING)
  var userOrigin: UserOrigin,

  @ManyToOne(cascade = [CascadeType.ALL])
  var userRole: UserRole,

  var birthDate: LocalDate?,
  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
    name = "user_like_property",
    joinColumns = [JoinColumn(name = "user_id", referencedColumnName = "id")],
    inverseJoinColumns = [JoinColumn(name = "property_id", referencedColumnName = "id")]
  )
  @JsonManagedReference
  var likedProperties: MutableList<Property> = mutableListOf()

) : AbstractEntity()

@Table(name = "user_role")
@Entity
class UserRole(
  var title: String,
  @Column(unique = true) // The unique slug to search the role      
  var slug: String,
  var description: String,
  var active: Boolean = true,
  @CreationTimestamp var creationDate: LocalDate,

  @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL]) @JsonManagedReference
  var permissions: MutableList<Permission> = mutableListOf()
) : AbstractEntity()

@Table(name = "permission")
@Entity
class Permission(
  var title: String,
  @Column(unique = true) // The unique slug to search the permission      
  var slug: String,
  var description: String,
  var active: Boolean = true,
  @CreationTimestamp var creationDate: LocalDate,

  @ManyToMany(fetch = FetchType.LAZY, cascade = [CascadeType.ALL]) @JsonBackReference
  var userRoles: MutableList<UserRole> = mutableListOf()
) : AbstractEntity()
