package com.klashz.api_reserva_samanes.tournament

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.Mapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/duo")
class DuoTournamentController(private val duoTournamentService: DuoTournamentService) {

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createDuoTournament(@RequestBody duoTournamentDto: DuoTournamentDto) : ResponseEntity<DuoTournamentEntity> {
        val duoTournamentEntity = duoTournamentService.createTournamentTwoGroup(duoTournamentDto)
        return ResponseEntity.status(201).body(duoTournamentEntity)
    }
}