package com.hferrer08.pokedex151.ui.screens.pokemonfavorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.hferrer08.pokedex151.data.local.FavoritesManager
import com.hferrer08.pokedex151.domain.model.Pokemon
import com.hferrer08.pokedex151.viewmodel.PokemonFavoritesViewModel

@Composable
fun PokemonFavoritesScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonFavoritesViewModel = viewModel(),
    onPokemonClick: (Int) -> Unit
) {
    val context = LocalContext.current
    val favoritesManager = remember(context) { FavoritesManager(context) }

    LaunchedEffect(Unit) {
        viewModel.loadFavorites(favoritesManager)
    }

    when {
        viewModel.isLoading -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Text(
                    text = "Cargando favoritos...",
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        viewModel.errorMessage != null -> {
            Column(
                modifier = modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Error: ${viewModel.errorMessage}",
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

        viewModel.favoritePokemonList.isEmpty() -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No tienes Pokémon favoritos todavía",
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        else -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Mis favoritos",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                LazyColumn(
                    contentPadding = PaddingValues(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(viewModel.favoritePokemonList) { pokemon ->
                        FavoritePokemonItem(
                            pokemon = pokemon,
                            onClick = { onPokemonClick(pokemon.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FavoritePokemonItem(
    pokemon: Pokemon,
    onClick: () -> Unit
) {
    val context = LocalContext.current
    val favoritesManager = remember(context) { FavoritesManager(context) }
    var isFavorite by remember { mutableStateOf(favoritesManager.isFavorite(pokemon.id)) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            AsyncImage(
                model = pokemon.imageUrl,
                contentDescription = pokemon.name,
                modifier = Modifier.size(72.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {
                androidx.compose.foundation.layout.Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "#${pokemon.id}",
                            style = MaterialTheme.typography.bodySmall
                        )
                        Text(
                            text = pokemon.name.replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.titleMedium
                        )
                    }

                    IconButton(
                        onClick = {
                            favoritesManager.toggleFavorite(pokemon.id)
                            isFavorite = favoritesManager.isFavorite(pokemon.id)
                        }
                    ) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                            contentDescription = if (isFavorite) "Quitar de favoritos" else "Agregar a favoritos"
                        )
                    }
                }
            }
        }
    }
}