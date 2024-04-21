package ar.edu.unlam.mobile.scaffolding.ui.screens.presentationScreen.data.model

import ar.edu.unlam.mobile.scaffolding.R
import javax.inject.Inject

class WallpaperLogos @Inject constructor() {
    val logos = listOf(
        Logo(R.drawable.iv_marvelone),
        Logo(R.drawable.iv_marveltwo),
        Logo(R.drawable.iv_marvelthree),
        Logo(R.drawable.iv_marvelfour),
        Logo(R.drawable.iv_marvelfive)
    )
}
