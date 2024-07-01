package com.klashz.api_reserva_samanes.tournament

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface DuoTournamentRepository : JpaRepository<DuoTournamentEntity, UUID> {
}