package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.R
import ar.edu.unlam.mobile.scaffolding.data.local.SuperHeroCombat

@Composable
fun StatsBattle(superHero: SuperHeroCombat?) {
    Row(
        modifier = Modifier
            .fillMaxWidth().padding(horizontal = 9.dp), horizontalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.statColorAttack))
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = superHero!!.attack.toString(), color = Color.Black)
        }
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.statColorDefense))
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = superHero!!.defense.toString(), color = Color.Black)
        }
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.statColorPenance))
                .weight(1f),
            contentAlignment = Alignment.Center
        ) {
            Text(text = superHero!!.damageAbs.toString(), color = Color.Black)
        }
    }
}