package com.klashz.api_reserva_samanes.group

import com.klashz.api_reserva_samanes.user.UserEntity
import jakarta.persistence.*
import org.apache.catalina.User
import java.util.*

@Entity
@Table(name = "groups")
data class GroupEntity(
    @field:Id
    @field:GeneratedValue( strategy = GenerationType.UUID)
    val id : UUID?,
    @field:Column(name = "nombre")
    var name : String,
    @field:Column(name = "imagen")
    val imageGroup : String?,

    @OneToOne
    @JoinColumn(name = "owner_id", referencedColumnName = "cedula")
    var owner : UserEntity?,

    @OneToMany(fetch = FetchType.LAZY,  mappedBy = "groupEntity", cascade = [CascadeType.ALL])
    var users: MutableList<UserEntity> = mutableListOf()
)
