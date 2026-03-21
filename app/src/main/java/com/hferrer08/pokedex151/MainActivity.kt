package com.hferrer08.pokedex151

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.hferrer08.pokedex151.ui.screens.pokemonlist.PokemonListScreen
import com.hferrer08.pokedex151.ui.theme.PokeDex151Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokeDex151Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PokemonListScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}