package ar.edu.unlam.mobile.scaffolding.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ar.edu.unlam.mobile.scaffolding.data.database.entities.SuperHeroEntity

@Dao
interface SuperHeroDao {
    @Query("SELECT * FROM superHero_table ORDER BY winRate DESC")
    suspend fun getHistorySuperHero(): List<SuperHeroEntity>

    @Query("UPDATE superHero_table SET winRate= :newWinRate WHERE name = :nameSuperHero")
    suspend fun updateWinRate(nameSuperHero: String, newWinRate: Int)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insert(superHero:SuperHeroEntity)


}