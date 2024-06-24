package com.klashz.api_reserva_samanes.user

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface UserRepository : JpaRepository<UserEntity,String> {

    fun findByEmail(email: String): Optional<UserEntity>
    fun findAllByState(state:UserState): List<UserEntity>
    @Query("SELECT u FROM UserEntity u WHERE YEAR(u.dateRegister) = :year AND MONTH(u.dateRegister) = :month")
    fun findAllByMonth(year:Int , month:Int): List<UserEntity>
}