# Recipes-Compose-demo
This is a Recipes demo app, using compose.

# Compile
You can download and compile the debug project, but for usage of google maps API you will need to build the release apk.

Use this in your terminal:
`./gradlew clean assembleRelease`

# Description

The following project is a demo project using Jetpack Compose and Clean Architecture using a custom mocked API from the following site:
https://www.mockable.io/

The app has three main views:
- Recipes List Screen: This view contains a list of recipes with the name and an image where the user can search using a custom search view input to search recipes based on the name or some ingredient that the recipe has.

- Recipe Detail Screen: This view contains a Detail with all the info about the recipe. It includes the name, image, ingredients and preparations. Also, this view includes a button to go to the next screen that shows a map.

- Recipe Map Screen: The final view in this version of the app includes a map and a marker to some location that it is going to be considered as the origin of the recipe.

# Tools Stack and Architecture

As said, the project uses Clean Architecture and MVVM with Jetpack Compose.


This project uses the following Android tool stack:

- Kotlin
- Jetpack Compose
- Coroutines
- ViewModel
- MutableState
- StateFlow
- Hilt
- Compose Navigation
- Google Maps API
- Material Design Components
- Dark Theme Support
- KTX core
- Mockk (for mocking on unit test)
- Retrofit
- Room Database
- Gson
- Coil (Image loading library)