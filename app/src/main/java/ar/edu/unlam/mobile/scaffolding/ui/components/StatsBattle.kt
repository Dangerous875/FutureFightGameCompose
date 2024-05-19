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
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.data.local.SuperHeroCombat


@Composable
fun StatsBattle(superHeroCom: SuperHeroCombat?, paddingStart:Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 370.dp, start = paddingStart.dp)
    ) {
        Box(
            modifier = Modifier
                .background(Color.Red)
                .height(30.dp)
                .width(72.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = superHeroCom!!.attack.toString(), color = Color.Black)
        }
        Box(
            modifier = Modifier
                .background(Color.Yellow)
                .height(30.dp)
                .width(72.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = superHeroCom!!.defense.toString(), color = Color.Black)
        }
        Box(
            modifier = Modifier
                .background(Color.Cyan)
                .height(30.dp)
                .width(72.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(text = superHeroCom!!.damagePenance.toString(), color = Color.Black)
        }
    }
}