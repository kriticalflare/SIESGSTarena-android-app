# Android client for [SIESGSTarena](http://arena.siesgst.ac.in/)

[![CI](https://github.com/kriticalflare/SIESGSTarena-android-app/workflows/CI/badge.svg)](https://github.com/kriticalflare/SIESGSTarena-android-app/actions)  [![GitHub license](https://img.shields.io/badge/License-GPL%20v3-yellow.svg)](https://opensource.org/licenses/GPL-3.0)

Built with the best of [Android Jetpack](https://developer.android.com/jetpack/) with a focus on clean architecture and providing a feature-packed, offline first experience.

<p float="left">
<img src="https://user-images.githubusercontent.com/42350771/84549282-41f21200-ad25-11ea-81ca-0abbe0432363.png" width="24%"/>
<img src="https://user-images.githubusercontent.com/42350771/84549345-64842b00-ad25-11ea-8b63-90296a8d7628.png" width="24%"/>
<img src="https://user-images.githubusercontent.com/42350771/84549347-65b55800-ad25-11ea-839f-5e8579ce73a1.png" width="24%"/>
<img src="https://user-images.githubusercontent.com/42350771/84549349-6817b200-ad25-11ea-879d-6ebc88bea4be.png" width="24%"/>
</p>

<p float="left">
<img src="https://user-images.githubusercontent.com/42350771/84549459-a1502200-ad25-11ea-91ef-78da9aa00555.png" width="24%"/>
<img src="https://user-images.githubusercontent.com/42350771/84549456-9f865e80-ad25-11ea-9a65-f45f0fc1136f.png" width="24%"/>
<img src="https://user-images.githubusercontent.com/42350771/84549461-a2814f00-ad25-11ea-8030-12621d0c48e9.png" width="24%"/>
<img src="https://user-images.githubusercontent.com/42350771/84549538-d2c8ed80-ad25-11ea-927b-08cad15dbde6.png" width="24%"/>
</p>

<p float="left">
<img src="https://user-images.githubusercontent.com/42350771/84549682-37844800-ad26-11ea-9e4c-992873284080.png" width="24%"/>
</p>

## Project Characteristics
- Offline first app
- Data is synced periodically in background so that latest data is shown even when offline
- Push notifications
- App shortcuts 
- Widgets to keep track of contests at a glance
- CI using github actions
- Linting using ktlint
- Reactive UI
- Single source of truth
- Dependency injection using koin

## Built With 🛠

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - Coroutines is the Android team's recommended solution for asynchronous programming on Android
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - ViewModel is designed to store and manage UI-related data in a lifecycle conscious way which allows data to survive configuration changes such as screen rotations.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - LiveData is lifecycle-aware observable data holder class for communicating between viewmodel and the view
  - [ViewBinding](https://developer.android.com/topic/libraries/view-binding) - Generates a binding class for each XML layout file present in that module and provides a type and null safe way of interacting with views.
  - [WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager) - WorkManager makes it easy to schedule deferrable, asynchronous tasks that are expected to run even if the app exits or the device restarts.
  - [Room](https://developer.android.com/topic/libraries/architecture/room) - The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
  - [Navigation Component](https://developer.android.com/guide/navigation) - The Navigation Architecture Component simplifies implementing navigation, while also helping you visualize your app's navigation flow. 
- [Koin](https://doc.insert-koin.io/#/) - A pragmatic lightweight dependency injection framework for Kotlin 
- [ThreeTenABP](https://github.com/JakeWharton/ThreeTenABP) - An adaptation of the JSR-310 backport for Android.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Firebase Cloud Messaging](https://firebase.google.com/docs/cloud-messaging) - FCM is a cross-platform messaging solution that lets you reliably send messages.
- [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.
- [Moshi Converter](https://github.com/square/retrofit/tree/master/retrofit-converters/moshi) - A Converter which uses Moshi for serialization to and from JSON.
- [Material Components for Android](https://github.com/material-components/material-components-android) - Modular and customizable Material Design UI components for Android


## Architecture

The app uses [MVVM (Model View View-Model)](https://developer.android.com/jetpack/docs/guide#recommended-app-arch) architecture.
![](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

## Building

You can either fork the project or clone it by running the following command in terminal

```
$ git clone https://github.com/kriticalflare/SIESGSTarena-android-app
```

**Install Android Studio**

Download and install Android Studio from [here](https://developer.android.com/studio/).

**Import the project into Android Studio.**

When Android Studio starts up, you will be prompted to create a new project or import an existing project. Select the
```import an existing project```, navigate and select the directory where you cloned the project.

**Add firebase to your app**

The app uses Firebase Cloud Messaging for the push notification functionality. To build the app successfully, you will need to create a `google-services.json` using the [firebase documentation](https://firebase.google.com/docs/android/setup)

## Contributing

Please read the contribution guidelines at [CONTRIBUTING.md](CONTRIBUTING.md).
