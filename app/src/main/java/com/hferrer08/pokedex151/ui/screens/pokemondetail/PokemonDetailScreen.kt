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