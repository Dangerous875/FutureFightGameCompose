package ar.edu.unlam.mobile.scaffolding.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import ar.edu.unlam.mobile.scaffolding.R

@Composable
fun IconPowerDetail(){
    Icon(
        painter = painterResource(id = R.drawable.ic_power_detail),
        contentDescription = null,
        tint = colorResource(id = R.color.iconColor),
        modifier = Modifier.size(30.dp)
    )
}