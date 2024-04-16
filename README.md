# Mobile Scaffolding

## Index

- [Configuración](#configuration)
- [Arquitectura Hexagonal](#arquitectura-hexagonal)
- [Domain](#domain)
    - [Modelos](#modelos)
    - [Use Cases](#use-cases--services)
- [UI](#ui)
    - [Composables](#composables)
    - [SPA](#single-activity-application)
    - [Packages](#packages-en-ui)
        - [Screens](#screens)
        - [Components](#componentes)
    - [Main Activity](#main-activity)
        - [Navigation](#navigation)
- [Data](#data)

Este repositorio se presenta como una referencia para organizar un proyecto de Android. La elección
de packages está basada en las recomendaciones de android [1] y en la arquitectura hexagonal.

![image](https://github.com/unlam-tec-movil/ScaffoldingV2/assets/5816687/b8a3641e-8c5f-48e0-a995-7a7b9594dae3)

# Configuration

El proyecto ejecuta dos workflows de github en cada PR. El primero realiza análisis estático de
código usando ktlint y gradle. El segundo ejecuta los tests de la aplicación.

Para configurar el proyecto en Android Studio, se debe de tener instalado el plugin
de [ktlint](https://plugins.jetbrains.com/plugin/15057-ktlint)

# Arquitectura Hexagonal

Propone independizar la lógica de negocio del context o de detalles de implementación.
Las Comunicaciones de entrada y salida dependen de la lógica de negocio y no al revés.

Esta arquitectura clasifica a los tipos de entrada y salida en 4:

* Eventos de negocio: (Interfaz de usuario)
* Administración: (Logging, CLI de Administración)
* Notificación: (SMS, Emails, etc)
* Peristencia: (Base de datos, Archivos)

## Componentes

### Puertos

Los accesos hacia la aplicación (en nuestro caso, la interacción del usuario), y los accesos desde
la aplicación hacia el exterior se realizan mediante Puertos de Entrada y Salida.
Dichos puertos definen interfaces de negocio. Es decir, reciben y devuelven modelos puros de
negocio.

### Adapters

La implementación de la comunicación de la aplicación con recursos externos se realiza mediante
adapters.
En nuestro cursada veremos que dichos adapters son los accesos a la base de datos y hacia los
servicios externos (implementaciones de Room y Retrofit)

## Diseño

### Esquema General

![image](https://upload.wikimedia.org/wikipedia/commons/thumb/7/75/Hexagonal_Architecture.svg/500px-Hexagonal_Architecture.svg.png)

### Interacción Puertos y Adapters

![AdaptersAndPorts](https://github.com/unlam-tec-movil/ScaffoldingV2/assets/5816687/32d0adfc-c6e6-4402-b1c7-9d69cf6a0d1a)

### Arquitectura Android y Hexa

![AndroidXHexa](https://github.com/unlam-tec-movil/ScaffoldingV2/assets/5816687/b8cecf19-6a73-466c-885a-294a7fe8b972)

# Domain

En esta capa se ubicará la lógida de negocio de nuestra aplicación.
Idealmente, debiera de haber un package por cada dominio[1] de negocio.

Dentro de cada dominio, se ubicarán los siguientes elementos: DI, Models, UseCases/Services.

## Inyección de dependencias

DI: Este package es para definir la inyección de dependencias[2] mediante un modulo.

## Modelos

Models: Este package tiene los modelos de negocio de este dominio. Idealmente debieran de ser
o Aggregates o al menos no anémicos.

### Aggregate Models

Los "aggregates" abarcan diversos modelos vinculados entre sí. Típicamente tendremos un modelo de,
en términos DER[3] una entidad fuerte y otras entidades débiles que dependen de la fuerte. Por
ejemplo, en un sistema de facturación, el modelo de factura es el agregado fuerte y los items de
factura son los agregados débiles. Al modelo
se accederá siempre a través de esta entidad fuerte.

Nota: Estas entidades son de negocio, no de base de datos. Por lo tanto, no deben de tener
anotaciones de persistencia.

### Anemic Models

Existe un anti patrón de diseño llamado "anemic model" que consiste en tener modelos que solo tienen
atributos y
getters. Esto rompe con la idea de "Programación orientada a objetos" que combina datos y procesos (
comportamiento)[4].

Idealmente, nuestros modelos de negocio debieran de poder tener comportamiento.

## Use Cases / Services

Estos objetos definirán firmas y comportamiento para los casos de uso del dominio. Exponen las
acciones que un tercero
puede hacer en nuestra aplicación. Habitualmente se los conoce como "servicios" o "casos de uso" y
abarcan varias
fuentes de información en caso de necesitar obtener estado de diferentes modelos del propio dominio.

Estas clases no debieran de depender de ninguna anotación o implementación ajena a lo que es el
dominio. Aquí no
debieramos de encontrarnos con anotaciones de Hilt (Inyección de dependencias de android) quizá si
de Dagger (inyección
de dependencias de Kotlin). Tampoco debieramos de encontrarnos con dependencias de acceso a bases de
datos como Room o
de comunicación HTTP como retrofit. Finalmente, tampoco debieran de haber viewmodel o livedata que
son componentes
Android.

# UI

En esta capa se diseña la interacción con el usuario. Aquí se definirán pantallas, componentes
visuales y el estado de
la información. Al ser el eslabón más cercano con el usuario, esta capta también es la que más
componentes "android"
tendrá. Por ejemplo, aquí estarán los viewmodels, se podrá usar livedata, etc.

## Composables

Actualmente, el elemento troncal para la composición de pantallas en Android son los composables de
la librería Jetpack
Compose. Esencialmente son funciones que reciben un estado y devuelven una función. Jetpack
interpreta a estos
componentes y renderiza las pantallas correspondientes.

## Single Activity Application

A raíz de la maduración de jetpack compose, hoy en día Android nos permite construir aplicaciones
usando un solo
activity. Esto reduce la complejudad y agiliza la navegación entre pantallas. Pero propone un nuevo
desafío: ¿Cómo
organizamos nuestros componentes para navegar entre pantallas? Una respuesta es el paradigma
contenedor / componente.

## Paradigma Contenedor Componente

Este paradigma propone organizar nuestros composables en dos grandes tipo. Uno como contenedor y
otros como componentes
reutilizables. Un contenedor agrupa componentes que pueden ser construidos y destruidos juntos. En
sí no definen
elementos visuales sino que reutilizar y organizan otros elementos visuales. Un componente por otro
lado, define
visualmente cómo se muestra la información deseada. Es decir: recibirá un estado y diseñará cómo se
muestra este estado.

## Packages en UI

Siguiendo el paradigma contenedor / componente, en UI tendremos dos grandes packages: screens y
components.

### Screens

Aquí estarán los contenedores de pantallas. Estos componentes orquestarán a los otros componentes y
definirán qué cosas
ve el usuario en cada pantalla. Cada Screen estará a compañado de su respectivo viewmodel quien es
el que se encarga de
manejar el estado que se muestra en la pantalla.

### Componentes

Aquí estarán los componentes visuales. Estos recibirán un estado desde una pantalla / screen y
definirán cómo se
muestra. Estos componentes pueden ser reutilizados en diferentes pantallas.

### View Models

Los view models son los contenedores de estado. Ya veremos en clase cómo tratar con ellos. Lo
imporante es que para
acceder a un viewmodel dentro de un composable de tipo Screen utilizamos la herramienta Hilt
llamando al método
hiltViewModel() como valor por defecto de dicho viewModel.

```kotlin
@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel = hiltViewModel()) {
}

```

> La organización de packages entre Screens y ViewModels puede variar acorde a la necesidad o el
> gusto de los desarrolladores: Los ViewModels pueden estar en el mismo package que los screens, o
> en un package separado.

## Main Activity

Este es el activity principal de nuestra aplicación. Aquí definiremos el theme, la navegación y el
scaffold principal.

### Navigation

#### Navigation Controller

Para la navegación utilizaremos la herramienta Navigation Controller de Jetpack. Esta herramienta
nos permite navegar
entre componentes en nuestra aplicación. Para hacerlo primero definimos un Host que es el lugar en
el que "traeremos"
las pantallas de nuestra aplicación. En este caso, lo ubicamos en el "body" del Scaffold. De este
modo traeremos las
pantallas dentro del contexto del Scaffold.

Dentro del host es que definiremos los "destinos" posibles para la navegación. Para ello insertamos
dentro del NavHost
las diferentes pantallas que definimos en el package de Screen. Como estas son composables, llamamos
a la función
"composable" dentro del navhost. Esta función recibe por parámetro un identificador "route" que será
el modo en el que
podremos acceder al composable a posteriori. También recibe un composable que es el Screen que
queremos mostrar.

Podemos tamibén recibir argumentos. Estos argumentos se definen en el "route" como un template de
texto. Luego, podemos
capturar el valor recibido y pasarseló al composable como parámetro.

Veamos un ejemplo:

```kotlin
NavHost(navController = controller, startDestination = "home") {
    // composable es el componente que se usa para definir un destino de navegación.
    // Por parámetro recibe la ruta que se utilizará para navegar a dicho destino.
    composable("home") {
        // Home es el componente en sí que es el destino de navegación.
        HomeScreen(modifier = Modifier.padding(paddingValue))
    }
    composable(
        route = "segundo/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType }),
    ) { navBackStackEntry ->
        val id = navBackStackEntry.arguments?.getInt("id") ?: 1
        SecondaryScreen(controller = controller, id = id)
    }
}
```

En el ejemplo tenemos dos rutas definidas. Una "home" y otra "segundo/{id}". La primera no recibe
argumentos y es la que
usamos también por defecto (definido en el parámetro starDestination del NavHost).

La segunda ruta recibe un parámetro de tipo ID. El segundo parámetro de esta función: "arguments"
define cómo tratamos
los argumentos recibidos en la ruta. En este caso, definimos que el argumento "id" es de tipo Int.

Finalmente la función que pasamos al composable, recibimos un parámetro de tipo NavBackStackEntry.
Este objeto nos
permite acceder al estado de la navegación actual. Hacemos esto para poder leer el argumento ID que
recibimos, ponerle
un valor por defecto y luego pasarlo al composable SecondaryScreen.

Este SecondaryScreen podrá interactuar con el id recibido. Por ejemplo, podría pasarseló a un
viewmodel para traer
información de un elemento de ID recibido.

#### Navigate

Una vez que definimos las rutas de destino, queda ver cómo accedemos a ellas en el resto de la
aplicación. Para ello
necesitamos llamar al método controler.navigate("ruta") donde "ruta" es el identificador que
definimos en el NavHost.

Si quisieramos ir hacia la home, habría que llamar a: controller.navigate("home").

Si quisieramos ir hacia la página "segundo" y pasarle un id, habría que llamar a:
controller.navigate("segundo/1")

Si queremos que un composable pueda navegar, deberá de recibir por parámetro un NavHostController.

# Data

En esta capa se encontrarán las implementaciones para la comunicación con servicios externos a la
apliocación. Ya sean bases de datos, servicios web, etc.

Idealmente, debiera de haber un package por cada dominio de negocio.

Hay dos packages que tendremos de base: DI y Repository. Ya hablaremos de ellos más adelante.

Hay otros packages que podrán existir de acuerdo a la necesidad. Si necesitamos implementar una
conexión hacia una API externa, podremos tener un package de nombre "network".
Si necesitamos implementar acceso a una base de dato local, podremos tener un package de nombre
"local". En "idioma" Clean

## Inyección de dependencias

DI: Este package es para definir la inyección de dependencias[2] mediante un modulo.

## Repositorio

Repository: Este package definirá la interfaz del repositorio para el dominio al que pertenece.
También incluirá las
implementaciones que construyamos de esta. Hay algunas particularidades aquí: La interfaz que
definimos aquí es de cara
al dominio.

Esto quiere decir que aquí expondremos al recurso al resto de la aplicación. Por ejemplo: si el
dominio es
Auto, aquí tendremos un repositorio de autos. La interfaz de este repositorio será la que exponga
los métodos de acceso
al recurso auto y la implementación será el modo en el que iremos a "buscar" a este recurso.

### Layer DATA en Android y Clean Architecture

En Android, la capa de datos es la que se encarga de la comunicación con servicios externos. Esto es
igual a lo que sucede en Clean Architecture.

Es por ello que la interfaz de los repositorios vive aquí en Data y no como un puerto del dominio.

De todos modos, recomendamos que las firmas que se definen aquí respondan a modelos de negocio y no
a modelos de Data.
Es decir,
devolver aquí modelos definidos en Domain y no los que hayamos definido en las implemnentaciones que
veremos más
adelante.

### Implementación default

En este package también tendremos una implementación default del repositorio. Esta implementación
funcionará como "
agregadora" de las diferentes implementaciones / adapters que existan en esta capa. Esto nos dará
flexibilidad para ir a
buscar rcursos de diferente modo.

## Adapters, packages opcionales

Aquí estarán los adapters de los recursos externos. A diferencia de lo que se construye dentro de
repository, las clases aquí son de cara a los recursos externos. Es decir, tendremos una
implementación por cada fuente de información. Puede darse el caso de que sean coincidentes los
recursos expuestos en el package repository y lo que vayamos a buscar como no. Podemos llegar a
necesitar ir a buscar a varias fuentes de información lo necesario para exponer un recurso.

### Network

Aquí estará la implementación del consumo de apis externas.

### Local

Aquí estará la implementación del consumo de bases de datos locales.

![Power](https://github.com/unlam-tec-movil/ScaffoldingV2/assets/5816687/200d9f10-2a71-409a-90ba-103ddffa9758)

[1]: https://martinfowler.com/bliki/DomainDrivenDesign.html

[2]: https://developer.android.com/training/dependency-injection/hilt-android

[3]: https://es.wikipedia.org/wiki/Diagrama_entidad-relaci%C3%B3n

[4]: https://martinfowler.com/bliki/AnemicDomainModel.html
