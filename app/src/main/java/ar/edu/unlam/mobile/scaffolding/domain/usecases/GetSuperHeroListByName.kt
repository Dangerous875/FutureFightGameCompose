package ar.edu.unlam.mobile.scaffolding.domain.usecases

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import ar.edu.unlam.mobile.scaffolding.core.repairImage
import ar.edu.unlam.mobile.scaffolding.data.database.entities.toEntity
import ar.edu.unlam.mobile.scaffolding.data.di.ImageCacheUtil
import ar.edu.unlam.mobile.scaffolding.data.di.NetworkUtils
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.data.local.model.toSuperHeroItem
import ar.edu.unlam.mobile.scaffolding.data.repository.SuperHeroRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSuperHeroListByName @Inject constructor(
    private val superHeroRepository: SuperHeroRepository,
    private val networkUtils: NetworkUtils,
    @field:SuppressLint("StaticFieldLeak") @ApplicationContext private val context: Context
) {

    suspend operator fun invoke(query: String): List<SuperHeroItem> = withContext(Dispatchers.IO) {
        val heroDataBase = superHeroRepository.getAllSuperHeroesFromDataBase()
        if (networkUtils.isInternetAvailable()) {
            val heroListFromApi = superHeroRepository.getSuperHeroListByName(query)
            val heroListFromApiOk = checkHeroListNulls(heroListFromApi)
            if (heroDataBase.isEmpty()) {
                superHeroRepository.deleteSuperHeroOffline()
                val heroListWithPath = heroListFromApiOk.take(5).map {
                    val path = getPathImage(it)
                    it.toEntity(path)
                }
                Log.i("cris", "$heroListWithPath")
                superHeroRepository.insertSuperHeroOffline(heroListWithPath)
            }
            heroListFromApiOk
        } else {
            val heroesFromDb =
                superHeroRepository.getAllSuperHeroesFromDataBase().map { it.toSuperHeroItem() }
            heroesFromDb
        }
    }

    private suspend fun getPathImage(hero: SuperHeroItem): String {
        return ImageCacheUtil.cacheImage(context, hero.image.url)!!
    }
}

private fun checkHeroListNulls(heroList: List<SuperHeroItem>): List<SuperHeroItem> {
    val badIDs = listOf("7", "16", "22", "51", "59", "84", "113", "147")

    for (hero in heroList) {
        if (hero.id in badIDs) {
            hero.repairImage()
        }

        if (hero.powerstats.intelligence == "null") {
            hero.powerstats.intelligence = "50"
        }
        if (hero.powerstats.strength == "null") {
            hero.powerstats.strength = "70"
        }
        if (hero.powerstats.speed == "null") {
            hero.powerstats.speed = "70"
        }
        if (hero.powerstats.durability == "null") {
            hero.powerstats.durability = "100"
        }
        if (hero.powerstats.power == "null") {
            hero.powerstats.power = "70"
        }
        if (hero.powerstats.combat == "null") {
            hero.powerstats.combat = "70"
        }
        if (hero.biography.fullName.isBlank() || hero.biography.fullName == "null") {
            hero.biography.fullName = hero.name
        }
        if (hero.biography.publisher.isBlank() || hero.biography.publisher == "null") {
            hero.biography.publisher = "Marvel Comics"
        }
    }
    return heroList
}

