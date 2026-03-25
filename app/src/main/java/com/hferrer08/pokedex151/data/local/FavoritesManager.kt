package com.hferrer08.pokedex151.data.local

import android.content.Context

class FavoritesManager(context: Context) {

    private val prefs = context.getSharedPreferences("pokedex_favorites", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_FAVORITES = "favorite_pokemon_ids"
    }

    fun getFavorites(): Set<String> {
        return prefs.getStringSet(KEY_FAVORITES, emptySet()) ?: emptySet()
    }

    fun isFavorite(pokemonId: Int): Boolean {
        return getFavorites().contains(pokemonId.toString())
    }

    fun toggleFavorite(pokemonId: Int) {
        val currentFavorites = getFavorites().toMutableSet()
        val id = pokemonId.toString()

        if (currentFavorites.contains(id)) {
            currentFavorites.remove(id)
        } else {
            currentFavorites.add(id)
        }

        prefs.edit().putStringSet(KEY_FAVORITES, currentFavorites).apply()
    }
}