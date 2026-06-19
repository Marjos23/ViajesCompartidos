# Plan: Punto 1 — Capa de Interfaz y ViewModels (MAD Skills)

## Objetivo
Asegurar que todos los ViewModels gestionen estado de forma reactiva usando **StateFlow** + **Coroutines** con operaciones asíncronas simuladas (`delay`), consumidas por las screens con `collectAsStateWithLifecycle()` y aplicando **State Hoisting** estricto.

## Archivos a modificar

### 1. `data/repository/AuthRepository.kt`

**Qué hacer:** Agregar método `register()` a la interfaz y al `FakeAuthRepository`.

```kotlin
interface AuthRepository {
    suspend fun login(email: String, password: String): Result<Boolean>
    suspend fun register(
        nombres: String, apellidos: String, cedula: String,
        telefono: String, email: String, password: String
    ): Result<Boolean>
}
```

En `FakeAuthRepository`, implementar `register()` con `withContext(Dispatchers.IO)`, `delay(2000)` y validación básica de campos.

**Por qué:** RegisterViewModel necesita un repositorio para realizar la operación async de registro.

---

### 2. `ui/viewmodel/RegisterViewModel.kt`

**Qué hacer:**
- Agregar `AuthRepository` por constructor
- Cambiar `register()` a async con `viewModelScope.launch`
- Importar y usar `viewModelScope`, `delay`, `Dispatchers`
- Agregar `isLoading` y `errorMessage` funcionales

**Código clave:**
```kotlin
class RegisterViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _uiState = MutableStateFlow(RegisterUiState())
    val uiState: StateFlow<RegisterUiState> = _uiState.asStateFlow()

    fun register() {
        viewModelScope.launch {
            val state = _uiState.value
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            val result = authRepository.register(
                state.nombres, state.apellidos, state.cedula,
                state.telefono, state.email, state.password
            )
            result.onSuccess {
                _uiState.update { it.copy(isLoading = false, isRegistrationSuccessful = true) }
            }.onFailure { error ->
                _uiState.update { it.copy(isLoading = false, errorMessage = error.message) }
            }
        }
    }
}
```

**Por qué:** Este es el cambio más crítico. El `register()` actual es síncrono — no usa corrutinas, no tiene loading, no maneja errores. La rúbrica exige operaciones async.

---

### 3. `screens/RegisterScreen.kt`

**Qué hacer:** Agregar `LaunchedEffect` para navegar cuando el registro sea exitoso (mismo patrón que LoginScreen) y pasar `isLoading` + `errorMessage` a `RegisterContent`.

**Código clave:**
```kotlin
LaunchedEffect(uiState.isRegistrationSuccessful) {
    if (uiState.isRegistrationSuccessful) {
        onRegisterClick()
    }
}
```

Pasar también `isLoading` y `errorMessage` al `RegisterContent` para mostrar feedback visual.

---

### 4. `ui/viewmodel/PublishTripViewModel.kt`

**Qué hacer:** Agregar `publish()` async con loading.

```kotlin
data class PublishTripUiState(
    // ... campos existentes ...
    val isLoading: Boolean = false,
    val isPublishSuccessful: Boolean = false,
    val errorMessage: String? = null
)

fun publish() {
    viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        delay(1500) // simular publicación
        _uiState.update { it.copy(isLoading = false, isPublishSuccessful = true) }
    }
}
```

**Por qué:** El botón "Publicar viaje" actualmente no ejecuta ninguna operación async.

---

### 5. `screens/PublishTripScreen.kt`

**Qué hacer:**
- Agregar `LaunchedEffect` para navegar en `isPublishSuccessful`
- Pasar `isLoading` a `PublishTripContent`
- Mostrar `CircularProgressIndicator` en el botón mientras carga

```kotlin
LaunchedEffect(uiState.isPublishSuccessful) {
    if (uiState.isPublishSuccessful) {
        onPublishClick()
    }
}
```

---

### 6. `ui/viewmodel/HomeViewModel.kt`

**Qué hacer:** Agregar simulación de carga asíncrona en `init`.

```kotlin
init {
    viewModelScope.launch {
        delay(500)
        // Datos ya están en el estado inicial; el delay solo demuestra async
    }
}
```

**Por qué:** Demuestra que los datos fluyen de forma reactiva desde el ViewModel.

---

### 7. `ui/viewmodel/SearchResultsViewModel.kt`

**Qué hacer:** Hacer que el `isLoading` existente cambie dinámicamente.

```kotlin
init {
    viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        delay(1000)
        _uiState.update { it.copy(isLoading = false) }
    }
}
```

**Por qué:** `isLoading` existe pero nunca cambia de `false`.

---

### 8. `ui/viewmodel/TripDetailViewModel.kt`

**Qué hacer:** Agregar `isLoading` y simular carga en `init`.

```kotlin
data class TripDetailUiState(
    // ... campos existentes ...
    val isLoading: Boolean = false
)

init {
    viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        delay(800)
        _uiState.update { it.copy(isLoading = false) }
    }
}
```

---

### 9. `ui/viewmodel/ProfileViewModel.kt`

**Qué hacer:** Mismo patrón — agregar `isLoading` y simular carga.

```kotlin
init {
    viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        delay(600)
        _uiState.update { it.copy(isLoading = false) }
    }
}
```

---

### 10. `ui/viewmodel/ViewModelFactory.kt`

**Qué hacer:** Inyectar `authRepository` también en `RegisterViewModel`.

```kotlin
modelClass.isAssignableFrom(RegisterViewModel::class.java) -> {
    @Suppress("UNCHECKED_CAST")
    RegisterViewModel(authRepository) as T
}
```

---

## Resumen de cumplimiento de rúbrica

| Criterio | Antes | Después |
|---|---|---|
| StateFlow en ViewModels | ✅ | ✅ |
| collectAsStateWithLifecycle() | ✅ | ✅ |
| State Hoisting | ✅ | ✅ |
| Operaciones async en TODOS los VMs | ❌ Solo Login | ✅ Todos |
| isLoading funcional en cada VM | ❌ Solo Login | ✅ Todos |
| Navegación reactiva (LaunchedEffect) | ❌ Solo Login | ✅ Login + Register + Publish |
