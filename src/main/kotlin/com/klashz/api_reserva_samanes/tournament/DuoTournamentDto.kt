package com.klashz.api_reserva_samanes.tournament

import java.util.UUID

data class DuoTournamentDto(val groupOne:UUID, val groupTwo:UUID, val numberOfMatches:Int, val startDate:String, val endDate:String)
