package com.klashz.api_reserva_samanes.user

import java.util.Optional

interface IUserService {

    fun  getUserByDni(dni:String) : Optional<UserEntity>
    fun  getUserByEmail(email: String) : Optional<UserEntity>
    fun  getAllUsers(): List<UserEntity>
    fun  saveUser(user: UserEntity)
    fun  updateUser(newUserData:UserEntity) : Boolean
    fun  deleteUserByDni(dni:String) : Boolean
    fun  getAllUserByState(state:UserState) : List<UserEntity>
    fun getAllUserRegisterByMonth(year:Int, month:Int) : List<UserEntity>
}