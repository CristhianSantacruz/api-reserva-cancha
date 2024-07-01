package com.klashz.api_reserva_samanes.tournament

import com.klashz.api_reserva_samanes.group.GroupEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.OneToOne
import jakarta.persistence.Table
import java.util.UUID

@Entity
@Table(name = "tournaments")
data class DuoTournamentEntity(
    @field:Id
    @field:GeneratedValue(strategy = GenerationType.UUID)
    var id: UUID?,
    @field:OneToOne
    @field:JoinColumn(name = "first_group", referencedColumnName = "grupo_uno")
    var firstGroup: GroupEntity,
    @field:OneToOne
    @field:JoinColumn(name = "second_group", referencedColumnName = "grupo_dos")
    var secondGroup: GroupEntity,
    @field:Column(name = "numero_de_partidos")
    var numberOfMatches : Int,
    @field:Column(name="inicio_torneo")
    var startDate: String,
    @field:Column(name = "fin_torneo")
    var endDate: String,
    @field:Column(name = "resultado")
    var result : String,
)