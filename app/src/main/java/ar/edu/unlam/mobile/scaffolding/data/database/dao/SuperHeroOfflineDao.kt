package ar.edu.unlam.mobile.scaffolding.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.database.entities.SuperHeroOfflineEntity

@Dao
interface SuperHeroOfflineDao {

    @Query("SELECT * From Hero_table")
    suspend fun getAllSuperHeroes():List<SuperHeroOfflineEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllHeroes(heroes:List<SuperHeroOfflineEntity>)

    @Query("DELETE FROM Hero_table ")
    suspend fun deleteAllHeroes()
}