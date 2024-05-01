package ar.edu.unlam.mobile.scaffolding.ui.screens.selectCharacterScreen

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import ar.edu.unlam.mobile.scaffolding.data.local.OrientationScreen
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.ui.components.SearchHero
import ar.edu.unlam.mobile.scaffolding.ui.components.SetOrientationScreen
import ar.edu.unlam.mobile.scaffolding.ui.navigation.Routes
import ar.edu.unlam.mobile.scaffolding.ui.screens.selectCharacterScreen.viewModel.SelectCharacterViewModel
import coil.compose.rememberAsyncImagePainter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
private lateinit var selectCharacterViewModel: SelectCharacterViewModel
private lateinit var player: SuperHeroItem
private lateinit var com: SuperHeroItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SelectCharacterScreen(navController: NavHostController) {

    selectCharacterViewModel = hiltViewModel()

    SetOrientationScreen(context = LocalContext.current, orientation = OrientationScreen.PORTRAIT.orientation )

    Scaffold(
        topBar = { TopBar(navController) },
        content = { ContentView(navController) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {
    val context = LocalContext.current
    val (expanded, setExpanded) = remember { mutableStateOf(false) }

    TopAppBar(
        modifier = Modifier.height(48.dp),
        title = {
            Text(
                text = "Character Selection",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                textAlign = TextAlign.Start
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(Routes.PresentationScreen.route)
            }) {
                Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Black),
        actions = {
            IconButton(onClick = {
                selectCharacterViewModel.initListHero()
            }) {
                Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
            }
            IconButton(onClick = { setExpanded(true) }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { setExpanded(false) }
            ) {
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    "Persons",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                        .fillMaxWidth()
                ) {

                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Text(
                        text = "Persons",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    "Settings",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                        .fillMaxWidth()
                ) {

                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = null,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Text(
                        text = "Settings",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                )
                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            Toast
                                .makeText(
                                    context,
                                    "Exit",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                        .fillMaxWidth()
                ) {

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                        contentDescription = null,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    Text(
                        text = "ExitToApp",
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(end = 16.dp)
                    )
                }

            }
        }
    )
}

@Composable
fun ContentView(navController: NavHostController) {
    val playerList by selectCharacterViewModel.superHeroListPlayer.collectAsState()
    var searchHeroPlayer by remember { mutableStateOf("") }
    val comList by selectCharacterViewModel.superHeroListCom.collectAsState()
    var searchHeroCom by remember { mutableStateOf("") }



    if (playerList.isNotEmpty() && comList.isNotEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 56.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                SearchHero(
                    query = searchHeroPlayer,
                    onQueryChange = { searchHeroPlayer = it },
                    onSearch = { selectCharacterViewModel.searchHeroByNameToPlayer(searchHeroPlayer) }
                )

                LazyRowWithImagesHeroPlayer(heroList = playerList)

                SearchHero(
                    query = searchHeroCom,
                    onQueryChange = { searchHeroCom = it },
                    onSearch = { selectCharacterViewModel.searchHeroByNameToCom(searchHeroCom) }
                )

                LazyRowWithImagesHeroCom(heroList = comList)

                Button(onClick = {
                    selectCharacterViewModel.setCombatData(player, com)
                    navController.navigate(Routes.SuperHeroCombatScreen.route)
                }) {
                    Text(text = "Start Combat")
                }

            }
        }
    } else {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(Modifier.align(Alignment.Center))
        }
    }


}

@Composable
fun LazyRowWithImagesHeroPlayer(heroList: List<SuperHeroItem>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        items(heroList) { hero ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(200.dp) // Ajusta el tamaño de los Cards según sea necesario
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { player = hero },
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(hero.image.url),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@Composable
fun LazyRowWithImagesHeroCom(heroList: List<SuperHeroItem>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        items(heroList) { hero ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(200.dp) // Ajusta el tamaño de los Cards según sea necesario
                    .clip(RoundedCornerShape(8.dp))
                    .clickable { com = hero },
                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
            ) {
                Image(
                    painter = rememberAsyncImagePainter(hero.image.url),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}





