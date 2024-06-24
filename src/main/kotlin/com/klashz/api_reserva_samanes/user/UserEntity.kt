package com.klashz.api_reserva_samanes.user

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Entity
@Table(name = "users")
data class UserEntity(

    @field:Id
    @field:Column(name = "cedula")
    @field:NotNull(message = "El dni no puede ser nulo")
    val dni : String,
    @Column(name = "nombre")
    var name : String,
    @field:Column(name = "apellido")
    var lastName : String,
    @field:NotNull(message = "El correo no puede ser nulo")
    @field:Email(message = "El correo deber ser un correo  electronico valido")
    @field:NotBlank(message = "El correo no puede estar en blanco")
    @field:Column(name = "correo")
    var email : String,
    @field:Column(name = "fechaRegistro")
    var dateRegister : LocalDateTime?,
    @field:Enumerated(EnumType.STRING)
    @field:Column(name = "estado")
   var  state :  UserState?,
    @field:Column(name = "telefono")
    @field:Size(min = 1, max = 20, message = "El rango excede")
    @field:NotBlank(message = "El telefono no puede estar vacio")
    var phone : String,
    @field:Column(name = "direccion")
    var direction: String,
    @field:Column(name = "imagen")
    var image:String?,
    )