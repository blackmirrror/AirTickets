package ru.blackmirrror.airtickets.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import ru.blackmirrror.airtickets.data.room.entities.FlightDBO

@Dao
interface FlightDAO {
    @Query("SELECT * FROM flights")
    suspend fun getAll(): List<FlightDBO>

    @Insert
    suspend fun insert(articles: List<FlightDBO>)

    @Delete
    suspend fun remove(articles: List<FlightDBO>)

    @Query("DELETE FROM flights")
    suspend fun clear()
}