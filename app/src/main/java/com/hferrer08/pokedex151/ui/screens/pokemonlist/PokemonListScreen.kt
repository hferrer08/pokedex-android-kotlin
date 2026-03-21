package com.hferrer08.pokedex151.ui.screens.pokemonlist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.hferrer08.pokedex151.domain.model.Pokemon
import com.hferrer08.pokedex151.viewmodel.PokemonListViewModel

@Composable
fun PokemonListScreen(
    modifier: Modifier = Modifier,
    viewModel: PokemonListViewModel = viewModel(),
    onPokemonClick: (Int) -> Unit
) {
    val pokemonList = viewModel.pokemonList
    val isLoading = viewModel.isLoading
    val errorMessage = viewModel.errorMessage
    val currentPage = viewModel.currentPage
    val totalPages = viewModel.totalPages

    when {
        isLoading && pokemonList.isEmpty() -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CircularProgressIndicator()
                Text(
                    text = "Cargando Pokémon...",
                    modifier = Modifier.padding(top = 16.dp)
                )
            }
        }

        errorMessage != null && pokemonList.isEmpty() -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Error: $errorMessage")
            }
        }

        else -> {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                Text(
                    text = "Pokédex 151",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(vertical = 16.dp)
                )

                Text(
                    text = "Página $currentPage de $totalPages",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                LazyColumn(
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(pokemonList) { pokemon ->
                        PokemonItem(
                            pokemon = pokemon,
                            onClick = { onPokemonClick(pokemon.id) }
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Button(
                        onClick = { viewModel.previousPage() },
                        enabled = currentPage > 1,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Anterior")
                    }

                    Button(
                        onClick = { viewModel.nextPage() },
                        enabled = currentPage < totalPages,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text("Siguiente")
                    }
                }
            }
        }
    }
}

@Composable
fun PokemonItem(pokemon: Pokemon,  onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        onClick = onClick
    ) {
        Row(
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
        }
    }
}