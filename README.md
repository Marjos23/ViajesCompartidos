# ViajesCompartidos (RutaShare) 🚗

Aplicación móvil para compartir viajes, desarrollada con las últimas tecnologías de Android (Modern Android Development - MAD).

## Características Implementadas

### Arquitectura y Patrones
- **Arquitectura de Capas:** Separación clara entre la capa de UI (Compose + ViewModels) y la capa de Datos (Repositorios).
- **Manejo de Estado Reactivo:** Uso de `StateFlow` y `SharedFlow` para una UI reactiva y eficiente.
- **State Hoisting:** Implementación estricta de elevación de estado para mejorar la testabilidad y reutilización de componentes.
- **Inyección de Dependencias Manual:** Uso de `ViewModelProvider.Factory` para gestionar las dependencias de los ViewModels.
- **Corrutinas y Flow:** Manejo de operaciones asíncronas en hilos secundarios utilizando `Dispatchers.IO`.

### Interfaz de Usuario (UI)
- **Jetpack Compose:** Interfaz moderna, declarativa y totalmente construida con componentes de Compose.
- **Navegación:** Gestión de rutas y pantallas mediante `Navigation Compose`.
- **Material 3:** Implementación de temas, colores y componentes basados en Material Design 3.

### Pantallas Principales
- **Login:** Validación de credenciales simulada con estados de carga y manejo de errores.
- **Home:** Visualización de búsquedas recientes y acceso rápido a funciones principales.
- **Publicar Viaje:** Formulario detallado para la creación de nuevos viajes compartidos.
- **Navegación Inferior (Bottom Bar):** Acceso rápido entre Inicio, Buscar, Publicar y Perfil.

## Tecnologías Utilizadas
- **Kotlin:** Lenguaje de programación principal.
- **Jetpack Compose:** Framework para la UI.
- **Kotlin Coroutines & Flow:** Para asincronía y flujos de datos.
- **ViewModel & Lifecycle:** Gestión del ciclo de vida y persistencia de estado de la UI.
- **Navigation Compose:** Para la navegación entre pantallas.

## Requisitos
- Android Studio Ladybug o superior.
- SDK de Android 24 (mínimo) hasta 36 (target).
