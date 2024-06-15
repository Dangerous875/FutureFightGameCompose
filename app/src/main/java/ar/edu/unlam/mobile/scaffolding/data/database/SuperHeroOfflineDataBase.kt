package ar.edu.unlam.mobile.scaffolding.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ar.edu.unlam.mobile.scaffolding.data.database.converter.Converters
import ar.edu.unlam.mobile.scaffolding.data.database.dao.SuperHeroOfflineDao
import ar.edu.unlam.mobile.scaffolding.data.database.entities.SuperHeroOfflineEntity

@Database(entities = [SuperHeroOfflineEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class SuperHeroOfflineDataBase:RoomDatabase() {

    abstract fun getSuperHeroOfflineDao():SuperHeroOfflineDao
}