package ar.edu.unlam.mobile.scaffolding.core

import ar.edu.unlam.mobile.scaffolding.data.local.SuperHeroCombat
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import kotlin.math.roundToInt
import kotlin.random.Random

fun SuperHeroItem.toSuperHeroCombat(): SuperHeroCombat =
    SuperHeroCombat(
        id = this.id,
        name = this.name,
        attack = calculateAttack(this),
        defense = calculateDefense(this),
        life = calculateLife(this),
        damageAbs = damageAbs(this),
        damagePenance = damagePenance(this),
        healingPotion = calculateHealing(this),
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
    return if (superHero.id == "17") {
        300
    } else {
        val attackFactor = 0.25
        val strength: Int = superHero.powerstats.strength.toInt()
        val power: Int = superHero.powerstats.power.toInt()
        (listOf(strength, power).average()).times(attackFactor).roundToInt()
    }
}

private fun calculateDefense(superHero: SuperHeroItem): Int {
    val defenseFactor = 0.30
    val durability: Int = superHero.powerstats.durability.toInt()
    val combat: Int = superHero.powerstats.combat.toInt()
    return (listOf(durability, combat).average()).times(defenseFactor).roundToInt()
}

private fun damageAbs(superHero: SuperHeroItem): Int {
    val bonus: Int =
        when (calculateDefense(superHero)) {
            in 1..10 -> 1   // 2
            in 11..20 -> 2  // 4
            in 21..30 -> 3  // 6
            else -> 0
        }
    return bonus
}

private fun damagePenance(superHero: SuperHeroItem): Int {
    val penance: Int =
        when (calculateLife(superHero)) {
            in 301..310 -> 0 //3
            in 311..320 -> 1 //6
            in 321..330 -> 2 //9
            else -> 0
        }
    return penance
}

private fun calculateHealing(superHero: SuperHeroItem): Int {
    val superHeroLife = calculateLife(superHero)
    val percentFix = 0.2
    return superHeroLife.times(percentFix).roundToInt()
}

// Función solicitada para aleatorizar el valor de ataque que tendrán los héroes
private fun directRandomAttack(attackValue: Int) : Int{
    return Random.nextInt(1, attackValue.plus(1))
}
// antes de implementar directRandomAttack(), verificar que la funcionalidad de las especiales esté Ok.

fun SuperHeroItem.repairImage(): SuperHeroItem {
    when (this.id) {
        "7" -> {
            this.image.url =
                "https://alchetron.com/cdn/adam-monroe-53b1e6e8-b572-4e18-92c5-e0d5b6f71dd-resize-750.jpeg"
        }

        "16" -> {
            this.image.url = "https://www.zonanegativa.com/imagenes/2019/01/booster-gold2.jpg"
        }

        "22" -> {
            this.image.url =
                "https://static.wikia.nocookie.net/marvelcomicsfanon/images/3/33/Shang-Chi-61615.jpg"
        }

        "51" -> {
            this.image.url =
                "https://i.ytimg.com/vi/9TsSDpBMuG4/maxresdefault.jpg"
        }

        "59" -> {
            this.image.url = "https://static.tvtropes.org/pmwiki/pub/images/aztek_large_9877.jpg"
        }

        "84" -> {
            this.image.url =
                "https://www.fortressofsolitude.co.za/wp-content/uploads/2015/12/open-uri20150422-20810-25n4lw_b0ad6d0f.png"
        }

        "113" -> {
            this.image.url = "https://s3.amazonaws.com/comicgeeks/characters/avatars/6558.jpg"
        }

        "147" -> {
            this.image.url =
                "https://static1.cbrimages.com/wordpress/wp-content/uploads/2021/11/Nightwing-and-the-Gotham-FCPD-Police.jpg"
        }
    }

    return this
}