package com.klashz.api_reserva_samanes.group

import java.util.Optional
import java.util.UUID

interface IGroupService {

    fun getGroupById(id: UUID): Optional<GroupEntity>
    fun getGroupByName(groupName: String): Optional<GroupEntity>
    fun getAllGroups(): List<GroupEntity>
    fun createGroup(groupDto: GroupDto)
    fun updateName(id:UUID, name: String)
    //fun addUserToGroup(idUser:String,idGroup:UUID)
    // fun deleteUserToGroup(idUser: String,idGroup:UUID)
    fun deleteGroupById(idGroup: UUID) : Boolean

}