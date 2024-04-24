package ar.edu.unlam.mobile.scaffolding.ui.screens.selectCharacterScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ar.edu.unlam.mobile.scaffolding.data.local.model.SuperHeroItem
import ar.edu.unlam.mobile.scaffolding.ui.screens.selectCharacterScreen.viewModel.SelectCharacterViewModel
import coil.compose.rememberAsyncImagePainter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
    fun SelectCharacterScreen(selectCharacterViewModel: SelectCharacterViewModel = hiltViewModel()) {
        Scaffold(
            content = { ContentView(selectCharacterViewModel) }
        )
    }

    @Composable
    fun ContentView(selectCharacterViewModel: SelectCharacterViewModel) {
        val heroList by selectCharacterViewModel.superHeroList.collectAsState()
        var searchText by remember { mutableStateOf("") }

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    SearchView(
                        query = searchText,
                        onQueryChange = { searchText = it },
                        onSearch = { selectCharacterViewModel.searchHeroByName(searchText) }
                    )

                    LazyRowWithImages(heroList)




                }
            }
        }


    @Composable
    fun SearchView(
        query: String,
        onQueryChange: (String) -> Unit,
        onSearch: () -> Unit
    ) {
        TextField(
            value = query,
            onValueChange = { onQueryChange(it) },
            placeholder = { Text(text = "Search...") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearch() }
            ),
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
    }

@Composable
fun LazyRowWithImages(heroList: List<SuperHeroItem>) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp)
    ) {
        items(heroList) { hero ->
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .size(200.dp) // Ajusta el tamaño de los Cards según sea necesario
                    .clip(RoundedCornerShape(8.dp)),
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
//@Composable
//fun LazyRowWithImages(heroList: List<SuperHeroItem>) {
//    LazyRow(modifier = Modifier.fillMaxSize()) {
//        items(heroList) { hero ->
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 16.dp)
//                    .height(250.dp),
//                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp),
//                shape = RoundedCornerShape(8.dp),
//                colors = CardDefaults.cardColors(
//                    containerColor = Color.White,
//                    contentColor = Color.Black
//                )
//            ) {
//                Image(
//                    painter = rememberAsyncImagePainter(hero.image.url),
//                    contentDescription = null,
//                    contentScale = ContentScale.Crop,
//                    modifier = Modifier.fillMaxSize()
//                )
//            }
//        }
//    }
//}

