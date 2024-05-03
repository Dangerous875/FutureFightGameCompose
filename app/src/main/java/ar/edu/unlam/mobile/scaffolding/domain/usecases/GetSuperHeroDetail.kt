package ar.edu.unlam.mobile.scaffolding.domain.usecases

import android.util.Log
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.data.repository.SuperHeroRepository
import javax.inject.Inject

class GetSuperHeroDetail @Inject constructor(private val repository: SuperHeroRepository) {
    operator fun invoke():SuperHeroItem?{
        return repository.getHeroDetail()
        Log.i("heroDetail"," domain : ${repository.getHeroDetail().toString()}")
    }
}