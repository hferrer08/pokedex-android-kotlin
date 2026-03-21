package com.hferrer08.pokedex151.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hferrer08.pokedex151.ui.screens.pokemondetail.PokemonDetailScreen
import com.hferrer08.pokedex151.ui.screens.pokemonlist.PokemonListScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppScreens.PokemonList.route,
        modifier = modifier
    ) {
        composable(AppScreens.PokemonList.route) {
            PokemonListScreen(
                onPokemonClick = { pokemonId ->
                    navController.navigate(
                        AppScreens.PokemonDetail.createRoute(pokemonId)
                    )
                }
            )
        }

        composable(
            route = AppScreens.PokemonDetail.route,
            arguments = listOf(
                navArgument("pokemonId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->
            val pokemonId = backStackEntry.arguments?.getInt("pokemonId") ?: 0

            PokemonDetailScreen(
                pokemonId = pokemonId
            )
        }
    }
}