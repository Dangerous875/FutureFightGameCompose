package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import ar.edu.unlam.mobile.scaffolding.R

@Composable
fun AttackEffect(attackEffect: Boolean, enableButton: Boolean) {
    val transition = rememberInfiniteTransition(label = "Infinity")
    val color = transition.animateColor(
        initialValue = colorResource(id = R.color.combatColorEffect1),
        targetValue = colorResource(
            id = R.color.combatColorEffect2
        ),
        animationSpec = infiniteRepeatable(animation = tween(300), repeatMode = RepeatMode.Restart),
        label = ""
    )
    if (attackEffect && !enableButton) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = color.value)
        )
    }
}