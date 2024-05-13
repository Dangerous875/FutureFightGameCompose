package ar.edu.unlam.mobile.scaffolding.core
import ar.edu.unlam.mobile.scaffolding.data.local.SuperHeroCombat
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import kotlin.math.roundToInt

fun SuperHeroItem.toSuperHeroCombat(): SuperHeroCombat =
    SuperHeroCombat(
        id = this.id,
        name = this.name,
        attack = calculateAttack(this),
        defense = calculateDefense(this),
        life = calculateLife(this),
        damageAbs = damageAbs(this),
        damagePenance = damagePenance(this),
        image = this.image
    )

private fun calculateLife(superHero: SuperHeroItem): Int {
    val lifeFactor = 0.3
    val lifeFix = 300
    val intelligence: Int = superHero.powerstats.intelligence.toInt()
    val speed: Int = superHero.powerstats.speed.toInt()
    val heroLife = (listOf(intelligence, speed).average()).times(lifeFactor).roundToInt()
    return heroLife.plus(lifeFix)
}

private fun calculateAttack(superHero: SuperHeroItem): Int {
    val attackFactor = 0.25
    val strength: Int = superHero.powerstats.strength.toInt()
    val power: Int = superHero.powerstats.power.toInt()
    return (listOf(strength, power).average()).times(attackFactor).roundToInt()
}

private fun calculateDefense(superHero: SuperHeroItem): Int {
    val defenseFactor = 0.30
    val durability: Int = superHero.powerstats.durability.toInt()
    val combat: Int = superHero.powerstats.combat.toInt()
    return (listOf(durability, combat).average()).times(defenseFactor).roundToInt()
}

//private fun lifeBonus(defenseValue: Int):Int{
//    val bonus: Int =
//        when (defenseValue) {
//            in 1..20 -> 60
//            in 21..40 -> 40
//            in 41..60 -> 20
//            else -> 0
//        }
//    return bonus
//}

private fun damageAbs(superHero: SuperHeroItem):Int{
    val bonus: Int =
        when(calculateDefense(superHero)){
            in 1..20 -> 2
            in 21..40 -> 4
            in 41..60 -> 6
            else -> 0
        }
    return bonus
}

private fun damagePenance(superHero: SuperHeroItem):Int{
    val penance: Int =
        when(calculateLife(superHero)){
            in 81..140 -> 3
            in 141.. 200 -> 6
            in 201..240 -> 9
            else -> 0
        }
    return penance
}