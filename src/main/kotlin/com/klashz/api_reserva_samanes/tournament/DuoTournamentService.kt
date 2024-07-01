package com.klashz.api_reserva_samanes.tournament

import com.klashz.api_reserva_samanes.group.IGroupService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class DuoTournamentService(private val repositoryTournament: DuoTournamentRepository, private val groupService:IGroupService) : IDuoTournamentService{
    override fun getTournamentTwoGroups(id: UUID): Optional<DuoTournamentEntity> {
        return repositoryTournament.findById(id)
    }

    override fun getAllTournamentTwoGroups(): List<DuoTournamentEntity> {
        return repositoryTournament.findAll()
    }
    @Transactional
    override fun createTournamentTwoGroup(tournament: DuoTournamentDto) : DuoTournamentEntity {
        val groupOneOptional = groupService.getGroupById(tournament.groupOne)
        val groupTwoOptional = groupService.getGroupById(tournament.groupTwo)
        if(groupOneOptional.isPresent){throw  RuntimeException("El primer grupo no existe") }
        if(groupTwoOptional.isPresent){throw RuntimeException("El segundo grupo no existe")}

        val tournamentEntity = DuoTournamentEntity(
            id = null,
            firstGroup =  groupOneOptional.get(),
            secondGroup =  groupTwoOptional.get(),
            startDate = tournament.startDate,
            endDate =tournament.endDate,
            numberOfMatches = tournament.numberOfMatches,
            result = ""
        )
       return  repositoryTournament.save(tournamentEntity);
    }

    override fun deleteTournamentTwoGroup(id: UUID): Boolean {
        val tournament = repositoryTournament.findById(id)
        if(tournament.isPresent){
            repositoryTournament.deleteById(id)
            return true
        }
        return false
    }

    override fun registerResult(idTournamentTwoGroupsEntity: UUID, result: String) : Boolean {
        val tournamentOptional = repositoryTournament.findById(idTournamentTwoGroupsEntity)
        if(tournamentOptional.isPresent){
            val tournament = tournamentOptional.get()
            tournament.result = result
            repositoryTournament.save(tournament)
            return true
        }
        return false;
    }

}