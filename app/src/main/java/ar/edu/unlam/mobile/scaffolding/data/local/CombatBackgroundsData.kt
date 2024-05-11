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
        Background("Mortal Kombat",R.drawable.iv_mortalkombatfight,R.raw.raw_mortalkombat),
        Background("Titan Planet", R.drawable.iv_titan_planet, R.raw.raw_arenafight),
        Background("Cyberpunk", R.drawable.iv_cyberpunk_escenario, R.raw.raw_arenafight),
        Background("Dune", R.drawable.iv_dune, R.raw.raw_arenafight),
        Background("Sky Platform", R.drawable.iv_escenario_plataforma_cielo, R.raw.raw_arenafight),
        Background("Simple Ring", R.drawable.iv_ring_sencillo, R.raw.raw_arenafight),
        Background("Terror Scene", R.drawable.iv_terror_scene, R.raw.raw_arenafight),
        Background("Ribbon Ring", R.drawable.iv_ribbon_ring, R.raw.raw_arenafight)
    )
}