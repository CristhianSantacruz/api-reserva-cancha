package com.klashz.api_reserva_samanes.user

import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import java.util.Optional

@RestController
@RequestMapping("/user")
class UserController(private val userService: IUserService) {


    @GetMapping("/hello", produces = [MediaType.TEXT_PLAIN_VALUE])
    @ResponseBody
    fun helloUser():String{
        return "Hello User Service"
    }
    @PostMapping("/save")
    fun saveUser(@Valid @RequestBody user: UserEntity): ResponseEntity<UserEntity> {
        userService.saveUser(user)
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.dni).toUri()).build()
    }

    @GetMapping("/{dni}")
    fun dni(@PathVariable dni:String):ResponseEntity<UserEntity>{
        val user : Optional<UserEntity> = userService.getUserByDni(dni)
        if(user.isPresent){
            return ResponseEntity.ok().body(user.get())
        }
        return ResponseEntity.notFound().build()
    }
    @GetMapping("/email/{email}")
    fun email(@PathVariable email:String):ResponseEntity<UserEntity>{
        val user : Optional<UserEntity> = userService.getUserByEmail(email)
        if(user.isPresent){
            return ResponseEntity.ok().body(user.get())
        }
        return ResponseEntity.notFound().build()
    }
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/all")
    fun getAllUsers():List<UserEntity>{
        return userService.getAllUsers();
    }

}