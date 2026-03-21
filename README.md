# 📱 Pokédex Android (Kotlin)

Aplicación móvil desarrollada en **Kotlin** como proyecto de aprendizaje, que consume la **PokeAPI** para mostrar un listado de Pokémon de la primera generación (151), con paginación y sistema de favoritos.

---

## 🚀 Objetivo del proyecto

Este proyecto tiene como objetivo aprender desarrollo móvil en Android utilizando Kotlin, entendiendo conceptos clave como:

- Consumo de APIs REST
- Manejo de estado
- Arquitectura básica en Android
- Listados con RecyclerView (equivalente a map en React 😄)
- Persistencia local (favoritos)
- Navegación entre pantallas

---

## 🧠 Conceptos aplicados

- 📡 Consumo de API (PokeAPI)
- 🔄 Paginación de datos
- ⭐ Manejo de favoritos (persistencia local)
- 🧩 Arquitectura por capas (UI / Data / Domain)
- 🧵 Corrutinas (async/await de Kotlin)
- 🧱 ViewModel (como un store de estado tipo React)

---

## 📦 Tecnologías utilizadas

- Kotlin
- Android Studio
- Retrofit (consumo de API)
- Gson / Moshi (serialización)
- Coroutines
- LiveData / StateFlow
- RecyclerView

---

## 📱 Funcionalidades

- ✅ Listado de Pokémon (Gen 1)
- ✅ Paginación (scroll infinito)
- ✅ Detalle de cada Pokémon
- ✅ Marcar como favorito ⭐
- ✅ Persistencia local de favoritos

---

## 🧱 Estructura del proyecto

```
app/
├── data/           # Consumo API, DTOs
├── domain/         # Modelos y lógica
├── ui/             # Pantallas (Activities / Fragments)
├── viewmodel/      # Manejo de estado
├── utils/          # Helpers
```

---

## 🔗 API utilizada

- https://pokeapi.co/

---

## ⚙️ Instalación y ejecución

1. Clonar repositorio:

```
git clone https://github.com/hferrer08/pokedex-android-kotlin.git
```

2. Abrir en **Android Studio**

3. Ejecutar en emulador o dispositivo físico

---

## 💾 Favoritos

Los Pokémon marcados como favoritos se almacenan localmente en el dispositivo, permitiendo:

- Persistencia al cerrar la app
- Acceso rápido a tus Pokémon preferidos

---

## 🧪 Estado del proyecto

🚧 En desarrollo — Proyecto enfocado en aprendizaje progresivo

---

## 📸 Próximas mejoras

- 🔍 Búsqueda por nombre
- 🎨 Mejoras de UI/UX
- 🧠 Cacheo de datos
- 🌐 Manejo de errores de red
- 📊 Animaciones

---

## 🤝 Contribución

Este es un proyecto personal de aprendizaje, pero cualquier sugerencia es bienvenida 🙌

---


## 👨‍💻 Autor

**Hubert Ferrer Guerrero**

- GitHub: https://github.com/hferrer08

---

## ⭐ Nota

Si te gustó el proyecto, dale una estrella ⭐ en el repo 😄
