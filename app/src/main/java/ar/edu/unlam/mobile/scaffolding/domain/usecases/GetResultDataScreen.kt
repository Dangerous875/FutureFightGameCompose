package ar.edu.unlam.mobile.scaffolding.domain.usecases

import ar.edu.unlam.mobile.scaffolding.data.local.ResultDataScreen
import ar.edu.unlam.mobile.scaffolding.data.repository.SuperHeroRepository
import javax.inject.Inject


class GetResultDataScreen @Inject constructor(private val repository: SuperHeroRepository) {

    operator fun invoke():ResultDataScreen{
        return repository.getResultDataScreen()
    }
}