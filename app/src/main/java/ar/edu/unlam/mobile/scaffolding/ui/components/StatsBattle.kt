package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.background
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
fun StatsBattle(superHero: SuperHeroCombat?, paddingStart: Int = 0, paddingEnd: Int = 0) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .width(250.dp)
            .padding(top = 370.dp, bottom = 12.dp, start = paddingStart.dp, end = paddingEnd.dp)
    ) {
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.statColorAttack))
                .height(30.dp)
                .width(72.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = superHero!!.attack.toString(), color = Color.Black)
        }
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.statColorDefense))
                .height(30.dp)
                .width(72.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = superHero!!.defense.toString(), color = Color.Black)
        }
        Box(
            modifier = Modifier
                .background(colorResource(id = R.color.statColorPenance))
                .height(30.dp)
                .width(72.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = superHero!!.damageAbs.toString(), color = Color.Black)
        }
    }
}