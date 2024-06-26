package com.klashz.api_reserva_samanes.group

import java.util.*

data class GroupDto(
    val id: UUID?,
    val name: String,
    val imageGroup: String?,
    val ownerId: String,
)
