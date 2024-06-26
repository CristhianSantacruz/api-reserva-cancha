package com.klashz.api_reserva_samanes.group

import com.klashz.api_reserva_samanes.user.IUserService
import com.klashz.api_reserva_samanes.user.UserEntity
import jakarta.transaction.Transactional
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.stereotype.Service
import java.util.*

@Service
class GroupService(private val groupRepository: GroupRepository,private val userService: IUserService) : IGroupService {
    override fun getGroupById(id: UUID): Optional<GroupEntity> {
        return groupRepository.findById(id)
    }

    override fun getGroupByName(groupName: String): Optional<GroupEntity> {
        return groupRepository.findByGroupName(groupName)
    }

    override fun getAllGroups(): List<GroupEntity> {
        return groupRepository.findAll().toList()
    }

    @Transactional
    override fun createGroup(groupDto: GroupDto) {
        val owner =userService.getUserByDni(groupDto.ownerId)
            .orElseThrow { IllegalArgumentException("Usuario no encontrado") }

        if (owner.group != null) {
            throw IllegalStateException("El usuario ya tiene un grupo asociado.")
        }


        val group = GroupEntity(
            id = null,
            name = groupDto.name,
            imageGroup = groupDto.imageGroup,
            owner = owner,
            users = mutableListOf()
        )
        if (groupRepository.existsByName(groupDto.name)) {
            throw IllegalArgumentException("El nombre del grupo ya existe.")
        }
        group.users.add(owner)
        groupRepository.save(group)

    }
    override fun updateName(id: UUID, name: String) {
        val userOptional = groupRepository.findById(id);
        if( userOptional.isPresent){
            val userEntity = userOptional.get()
            userEntity.name = name
            groupRepository.save(userEntity)
        }
    }





    override fun deleteGroupById(idGroup: UUID) :Boolean {
        val user  = groupRepository.findById(idGroup)
        if (user.isPresent) {
            user.get().id?.let { groupRepository.deleteById(it) }
            return true
        } else return false
    }
}