package com.klashz.api_reserva_samanes.user

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.Optional

@Service
class UserService(private val userRepository: UserRepository) : IUserService {


    override fun getUserByDni(dni: String): Optional<UserEntity> {
         val user : Optional<UserEntity>  = userRepository.findById(dni)
         return user
    }

    override fun getUserByEmail(email: String): Optional<UserEntity> {
        return userRepository.findByEmail(email)

    }

    override fun getAllUsers(): List<UserEntity> {
        return userRepository.findAll()
    }

    override fun saveUser(user: UserEntity) {
        user.dateRegister = LocalDateTime.now()
        user.state = UserState.ACTIVE
        if(user.image?.isEmpty() == true) {
            user.image = ""
        }
        userRepository.save(user)

    }

    override fun updateUser(newUserData: UserEntity) : Boolean {
        val userOptional : Optional<UserEntity>  = userRepository.findById(newUserData.dni)
        if(userOptional.isPresent){
            val user : UserEntity  = userOptional.get()
            user.state = newUserData.state
            user.name = newUserData.name
            user.phone = newUserData.phone
            user.email = newUserData.email
            user.dateRegister = newUserData.dateRegister
            user.direction = user.direction
            user.lastName = newUserData.lastName
            userRepository.save(user)
            return true
        }
        return false

    }

    override fun deleteUserByDni(dni: String): Boolean {
        val userOptional : Optional<UserEntity>  = userRepository.findById(dni)
        if(userOptional.isPresent){
            userRepository.deleteById(userOptional.get().dni)
            return true
        }
        return false
    }

    override fun getAllUserByState(state: UserState): List<UserEntity> {
       return userRepository.findAllByState(state)
    }

    override fun getAllUserRegisterByMonth(year: Int, month: Int): List<UserEntity> {
        return userRepository.findAllByMonth(year,month)
    }
}