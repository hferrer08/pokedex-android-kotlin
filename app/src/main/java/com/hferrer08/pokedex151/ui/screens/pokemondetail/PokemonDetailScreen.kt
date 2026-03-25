package com.hferrer08.pokedex151.ui.screens.pokemondetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hferrer08.pokedex151.data.repository.PokemonRepository
import coil.compose.AsyncImage
import com.hferrer08.pokedex151.data.local.FavoritesManager
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

@Composable
fun PokemonDetailScreen(
    pokemonId: Int
) {
    val repository = PokemonRepository()
    val viewModel: PokemonDetailViewModel = viewModel(
        factory = PokemonDetailViewModelFactory(repository)
    )

    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(pokemonId) {
        viewModel.loadPokemon(pokemonId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            uiState.isLoading -> {
                CircularProgressIndicator()
            }

            uiState.error != null -> {
                Text(
                    text = uiState.error ?: "Error desconocido",
                    color = MaterialTheme.colorScheme.error
                )
            }

            uiState.pokemon != null -> {
                val pokemon = uiState.pokemon!!

                val context = LocalContext.current
                val favoritesManager = remember(context) { FavoritesManager(context) }
                var isFavorite by remember(pokemon.id) {
                    mutableStateOf(favoritesManager.isFavorite(pokemon.id))
                }

                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {

                        AsyncImage(
                            model = pokemon.sprites.front_default,
                            contentDescription = pokemon.name,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(200.dp),
                            alignment = Alignment.Center
                        )

                        Text(
                            text = pokemon.name.replaceFirstChar { it.uppercase() },
                            style = MaterialTheme.typography.headlineMedium
                        )
                        Button(
                            onClick = {
                                favoritesManager.toggleFavorite(pokemon.id)
                                isFavorite = favoritesManager.isFavorite(pokemon.id)
                            }
                        ) {
                            Icon(
                                imageVector = if (isFavorite) Icons.Filled.Star else Icons.Outlined.StarBorder,
                                contentDescription = null
                            )

                            Spacer(modifier = Modifier.width(8.dp))

                            Text(
                                text = if (isFavorite) "Quitar de favoritos" else "Agregar a favoritos"
                            )
                        }

                        Text(text = "Pokédex ID: ${pokemon.id}")
                        Text(text = "Altura: ${pokemon.height}")
                        Text(text = "Peso: ${pokemon.weight}")

                        Text(text = "Tipos:")

                        pokemon.types.forEach { typeSlot ->
                            Text(text = "- ${typeSlot.type.name}")
                        }
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}