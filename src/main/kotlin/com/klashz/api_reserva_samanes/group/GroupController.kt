package com.klashz.api_reserva_samanes.group

import org.apache.coyote.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/group")
class GroupController(private val groupService: IGroupService) {


    @PostMapping("/create/{idUser}")
    @ResponseStatus(HttpStatus.CREATED)
    fun createGroup(@RequestBody groupDto: GroupDto,@PathVariable("idUser") idUser: String): ResponseEntity<GroupEntity> {
        val group =  groupService.createGroup(groupDto,idUser)
        return ResponseEntity.status(201).body(group);
    }


    @GetMapping("/{id}")
    fun getGroupById(@PathVariable id: UUID):ResponseEntity<GroupEntity>{
        val groupOptional  = groupService.getGroupById(id)
        if (groupOptional.isPresent){
            return ResponseEntity.ok(groupOptional.get())
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/name/{name}")
    fun getGroupByName(@PathVariable name: String): ResponseEntity<GroupEntity>{
        val groupOptional = groupService.getGroupByName(name)
        if (groupOptional.isPresent){
            return ResponseEntity.ok(groupOptional.get())
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/all")
    fun getAllGroups(): ResponseEntity<List<GroupEntity>>{
        return ResponseEntity.ok(groupService.getAllGroups())
    }

    @PostMapping("/{idUser}/group/{idGroup}")
    fun addUserToGroup(@PathVariable idGroup: UUID, @PathVariable idUser: String): ResponseEntity<GroupEntity>{
        return ResponseEntity.ok(groupService.addUserToGroup(idUser,idGroup))
    }

    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/update/{id}/name/{name}")
    fun updateNameByGroup(@PathVariable id:UUID,@PathVariable name: String){
        groupService.updateName(id,name)

    }
    @DeleteMapping("/delete/{id}")
    fun deleteGroupById(@PathVariable id: UUID):ResponseEntity<Boolean>{
        return if (groupService.deleteGroupById(id)) ResponseEntity.ok(true) else ResponseEntity.notFound().build()
    }
}