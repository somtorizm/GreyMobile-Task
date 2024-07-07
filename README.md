# Grey Android Engineer Technical Assessment

## Overview
This document outlines the implementation details and guidelines for the Grey Android Engineer Technical Assessment. The goal is to develop an Android app using modern Android development practices, specifically Jetpack Compose, for searching GitHub repositories and retrieving user information.

## Architecture
The app is built using the Model-View-ViewModel (MVVM) architecture, which promotes a clear separation of concerns and enhances testability.

## Libraries and Tools
- **Network Library**: Retrofit
- **UI**: Jetpack Compose
- **Dependency Injection**: Dagger Hilt (including Hilt for ViewModel)
- **Navigation**: Navigation Component with NavHost for full-grown Compose navigation
- **Unit Testing**: MockK, JUnit
- **Image Loading**: Coil for Compose

## Project Structure
The project is structured as follows:
- `data`: Contains data models and repository classes for network operations.
- `di`: Contains Dagger Hilt modules for dependency injection.
- `network`: Contains Retrofit service interfaces and network-related classes.
- `ui`: Contains all UI-related components, including screens and composables.
- `viewmodel`: Contains ViewModel classes to handle UI logic and data.
- `util`: Contains utility classes and functions.

## Screens
### Home Screen
- The starting destination of the app.
- Navigation options to both the repository screen and user screen.

### Repository Screen (Search Repos)
- Input field for search keywords.
- List of search results displaying repository name, number of stars, and issues.
- Handles empty states and differentiates between no results and no user input states.

### User Screen (Find User)
- Input field for GitHub username.
- Button to submit the search.
- Navigates to a new screen displaying user details if the user exists.

### User Details Screen
- Displays basic user information (username, avatar, followers, following).
- List of user repositories.
- Handles cases when the user has no repositories.
- Option to navigate back to the previous screen.

## API References
- [Search Repositories](https://developer.github.com/v3/search/#search-repositories)
- [Repositories](https://developer.github.com/v3/repos/)
- [Users](https://developer.github.com/v3/users/)

## Design
The app's design can be found on [Figma](https://www.figma.com/file/JzhqYEnWurXs3peqPJL6UG/Android-Developer-Interview-Live-Test?node-id=0%3A1).

## Key Focus Areas
- **Architecture**: Proper implementation of MVVM.
- **Code Quality**: Maintain high code quality and follow coding conventions.
- **App Performance**: Ensure the app performs efficiently.
- **Error Handling**: Proper handling of network errors (e.g., 404, 500 response codes, and IOExceptions).
- **Unit Tests**: Implement comprehensive unit tests.

## Nice to Have
- Additional features or design enhancements.
- Splash screen during app startup.

## Getting Started
### Prerequisites
- Android Studio
- Knowledge of Kotlin and Jetpack Compose

### Setup
1. Clone the repository: `git clone <repository_url>`
2. Open the project in Android Studio.
3. Build the project and ensure all dependencies are resolved.
4. Run the app on an emulator or physical device.

### Running Tests
- Unit tests can be run using the following command in Android Studio: `./gradlew test`


