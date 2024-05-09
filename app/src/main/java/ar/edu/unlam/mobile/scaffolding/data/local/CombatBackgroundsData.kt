package ar.edu.unlam.mobile.scaffolding.data.local

import ar.edu.unlam.mobile.scaffolding.R
import javax.inject.Inject

data class Background(val name: String, val background: Int, val theme: Int)

class CombatBackgroundsData @Inject constructor() {

    val combatBackgroundsData = listOf(
        Background("Arena", R.drawable.iv_combatscreen1, R.raw.raw_arenafight),
        Background("Dragon Ball",R.drawable.iv_dragonballfight,R.raw.raw_dragonball),
        Background("Star Wars",R.drawable.iv_starwarsfight,R.raw.raw_starwars),
        Background("Street Fighters",R.drawable.iv_streetfighterfight,R.raw.raw_streetfighters),
        Background("Mortal Kombat",R.drawable.iv_mortalkombatfight,R.raw.raw_mortalkombat)
    )
}