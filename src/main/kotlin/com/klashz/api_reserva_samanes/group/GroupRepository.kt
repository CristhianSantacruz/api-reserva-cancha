package com.klashz.api_reserva_samanes.group

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional
import java.util.UUID

@Repository
interface GroupRepository : JpaRepository<GroupEntity,UUID> {

    fun findByName(name: String): Optional<GroupEntity>

    fun existsByName(name: String): Boolean
}