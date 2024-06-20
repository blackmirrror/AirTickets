package ru.blackmirrror.airtickets.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.blackmirrror.airtickets.data.room.entities.TicketDBO

@Dao
interface TicketDAO {
    @Query("SELECT * FROM tickets")
    suspend fun getAll(): List<TicketDBO>

    @Insert
    suspend fun insert(articles: List<TicketDBO>)

    @Delete
    suspend fun remove(articles: List<TicketDBO>)

    @Query("DELETE FROM tickets")
    suspend fun clear()
}