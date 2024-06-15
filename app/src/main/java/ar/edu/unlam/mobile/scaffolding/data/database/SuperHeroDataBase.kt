package ar.edu.unlam.mobile.scaffolding.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ar.edu.unlam.mobile.scaffolding.data.database.dao.SuperHeroDao
import ar.edu.unlam.mobile.scaffolding.data.database.entities.SuperHeroEntity


@Database(entities = [SuperHeroEntity::class], version=1)
abstract class SuperHeroDataBase:RoomDatabase(){
    abstract fun getSuperHeroDao():SuperHeroDao
}