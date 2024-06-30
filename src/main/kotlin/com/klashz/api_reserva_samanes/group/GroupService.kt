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
        return groupRepository.findByName(groupName)
    }

    override fun getAllGroups(): List<GroupEntity> {
        return groupRepository.findAll().toList()
    }
    // dto group tiene name y image
    @Transactional
    override fun createGroup(groupDto: GroupDto, idUser: String) : GroupEntity {
       val owner =userService.getUserByDni(idUser)
            .orElseThrow { IllegalArgumentException("Usuario no encontrado") }

        if (owner.groupEntity != null) {
            throw RuntimeException("User already owns a group")
        }

        if (groupRepository.existsByName(groupDto.name)) {
            throw RuntimeException("Ya existe un grupo con ese nombre")
        }

        val group = GroupEntity(
            id = null,
            name = groupDto.name,
            imageGroup = groupDto.imageGroup,
            owner = owner,
            users = mutableListOf(owner)
        )
        owner.groupEntity = group
        val groupReturn = groupRepository.save(group);
        userService.saveUser(owner)
        return groupReturn

    }
    override fun updateName(id: UUID, name: String) {
        val userOptional = groupRepository.findById(id);
        if( userOptional.isPresent){
            val userEntity = userOptional.get()
            userEntity.name = name
            groupRepository.save(userEntity)
        }
    }

    override fun addUserToGroup(idUser: String, idGroup: UUID) : GroupEntity{
        val userOptional : Optional<UserEntity> = userService.getUserByDni(idUser);
        val groupOptional : Optional<GroupEntity> = groupRepository.findById(idGroup);
        if(userOptional.isEmpty) throw  RuntimeException("User not found")
        if(groupOptional.isEmpty) throw RuntimeException("Group Not Found")
        val user  = userOptional.get()
        val group = groupOptional.get();

        if(group.users.size>=16){
            throw RuntimeException("Group FULL")
        }

        if(group.users.contains(user)) throw RuntimeException("User Exists in the Group")

        user.groupEntity = group
        group.users.add(user)
        userService.saveUser(user)
        return groupRepository.save(group)




    }


    override fun deleteGroupById(idGroup: UUID) :Boolean {
        val user  = groupRepository.findById(idGroup)
        if (user.isPresent) {
            user.get().id?.let { groupRepository.deleteById(it) }
            return true
        } else return false
    }
}