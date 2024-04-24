package ar.edu.unlam.mobile.scaffolding.data.di

import ar.edu.unlam.mobile.scaffolding.data.network.SuperHeroApiClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object NetworkProvider {

    @Singleton
    @Provides
    fun getRetrofitProvider(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://superheroapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun getSuperHeroApiClient(retrofit: Retrofit):SuperHeroApiClient{
        return retrofit.create(SuperHeroApiClient::class.java)
    }


}