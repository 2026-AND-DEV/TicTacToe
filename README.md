# Tic Tac Toe
A Tic Tac Toe game built using Jetpack Compose, following Clean Architecture, MVVM and TDD.

**Game Rules**
- X always goes first.
- Players cannot play on a played position.
- Players alternate placing X’s and O’s on the board until either:
	- One player has three in a row, horizontally, vertically or diagonally
	- All nine squares are filled.
- If a player is able to draw three X’s or three O’s in a row, that player wins.
- If all nine squares are filled and neither player has three in a row, the game is a draw.

## Architecture
- Presentation - ViewModel + JetPack Compose
- Domain - Use Case + Models

## Tech Stack
- Kotlin
- Jetpack Compose
- Navigation
- MVVM + Clean architecture + TDD
- Coroutines + Flow + ViewModel
- Hilt - Dependency Injection
- Unit tests - JUnit 5, MockK, Turbine
- Instrumentation tests - Junit, Compose Test
