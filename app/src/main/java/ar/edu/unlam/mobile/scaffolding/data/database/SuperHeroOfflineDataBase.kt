package ar.edu.unlam.mobile.scaffolding.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.mobile.scaffolding.data.database.dao.SuperHeroOfflineDao
import ar.edu.unlam.mobile.scaffolding.data.database.entities.SuperHeroOfflineEntity

@Database(entities = [SuperHeroOfflineEntity::class], version = 1)
abstract class SuperHeroOfflineDataBase:RoomDatabase() {

    abstract fun getSuperHeroOfflineDao():SuperHeroOfflineDao
}