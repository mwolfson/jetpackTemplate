# Jetpack Compose Navigation and Material3 Starter Template

This project is designed to help you get started with a simple Modern Android Development (MAD) project.

It is purpose designed to be *very* simple, as a way to get started, so you can add your own customizations.

## Template Contents

Project includes:
* 100% Kotlin
* Material3 Implementation:
  * Typography
  * Color
  * Dimensions
  * Shape
* Jetpack Compose Navigation
  * Deep linking
* Jetpack Compose screens
  * LazyList
  * Input/Button
* Material Icons
  
It *does not* include:
* Architecture guidance
  * No Use-cases, etc
* Additional frameworks
  * No dependency injection

### Additions

#### Color

The [Color.kt](com/wolfsoft/demo/ui/theme/Color.kt) file is formatted so you can copy/paste a color definition directly from the [Material Theme Builder](https://material-foundation.github.io/material-theme-builder/#/custom)

It also includes color definitions for all the original M2 colors.

![colors](assets/colors.png)

#### Static Analysis

I included static analysis tooling in the build for this project. I like to use these tools to maintain consistency, so include them in all my projects.

To run static analysis on this project, execute:

```gradle
./gradlew lint ktlintcheck detekt
```

#### Remote Image Caching (Coil)

I'm using the [Coil](https://coil-kt.github.io/coil/) image caching library to make the demo a bit nicer.

## Demo

![demoGif](assets/demoGif.gif)

## How to Use

[Creating a Repository From A Template](https://docs.github.com/en/repositories/creating-and-managing-repositories/creating-a-repository-from-a-template)

## Near Future Plans

* Jetpack Compose Tests
* Repository

## Comments Welcome
Please contact me if you have any comments or suggestions.
[Twitter: mikewolfson](https://twitter.com/mikewolfson)


