package ar.edu.unlam.mobile.scaffolding.data.di

import android.content.Context
import androidx.room.Room
import ar.edu.unlam.mobile.scaffolding.data.database.SuperHeroDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {
    private const val SUPERHERO_DATABASE_NAME="superhero database"
    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context)= Room.databaseBuilder(context,SuperHeroDataBase::class.java,
        SUPERHERO_DATABASE_NAME).build()
    @Singleton
    @Provides
    fun provideSuperHeroDataBase(db:SuperHeroDataBase)=db.getSuperHeroDao()
}