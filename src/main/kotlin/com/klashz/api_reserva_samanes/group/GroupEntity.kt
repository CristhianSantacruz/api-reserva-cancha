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

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "group")
    @JoinColumn(name = "owner_dni")
    var owner : UserEntity?,

    @OneToMany(mappedBy = "members")
    var users: MutableList<UserEntity>?
){

}
