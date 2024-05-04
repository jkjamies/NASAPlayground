# Improvement Ideas

- Consider combining NASA services - project uses separate modules for demonstration purposes
- Add parameters to allow for different dates or date ranges
- Strings resources and localization
- Better failure or offline handling - use error results instead of just null values
- Accessibility improvements
- Kotlin/Wasm to host documentation through GitHub pages via workflow
- Better linting and code quality tooling - jacoco, detekt, ktlint, qodana, sonarqube, etc
- Architectural testing using konsist
- Add a work manager to schedule the updates so data is always up to date when user opens the app
- Notifications when the work manager updates the data
- Favorites that persist either outside of the date ranges or just indicators for an exclusive list
- Change from DEMO_KEY to a real key, and abstract and hide it properly - also resource the
  endpoints

TODO for finishing short project

- Add unit tests for neos
- Create ui for neos list on home screen
- Create ui for neo detail screen
- Create navigation pattern for screens
- Create shared ui components between apod and neo details
- Add unit tests and ui tests for app