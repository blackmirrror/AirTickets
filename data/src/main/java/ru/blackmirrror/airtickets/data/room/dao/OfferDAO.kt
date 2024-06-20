package ru.blackmirrror.airtickets.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.blackmirrror.airtickets.data.room.entities.OfferDBO

@Dao
interface OfferDAO {
    @Query("SELECT * FROM offers")
    suspend fun getAll(): List<OfferDBO>

    @Insert
    suspend fun insert(articles: List<OfferDBO>)

    @Delete
    suspend fun remove(articles: List<OfferDBO>)

    @Query("DELETE FROM offers")
    suspend fun clear()
}