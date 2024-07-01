package com.klashz.api_reserva_samanes.tournament

import java.util.Optional
import java.util.UUID

interface IDuoTournamentService {

    fun getTournamentTwoGroups(id:UUID) : Optional<DuoTournamentEntity>
    fun getAllTournamentTwoGroups() : List<DuoTournamentEntity>
    fun createTournamentTwoGroup(tournament:DuoTournamentDto) : DuoTournamentEntity
    fun deleteTournamentTwoGroup(id: UUID) : Boolean
    fun registerResult(idTournamentTwoGroupsEntity:UUID,result: String) : Boolean
}