# Improvement Ideas

- Consider combining NASA services - project uses separate modules for demonstration purposes
- Add parameters to allow for different dates or date ranges
- Strings resources and localization
- Accessibility improvements
- Kotlin/Wasm to host documentation through GitHub pages via workflow
- Better linting and code quality tooling - jacoco, detekt, ktlint, qodana, sonarqube, etc
- Architectural testing using konsist
- Add a work manager to schedule the updates so data is always up to date when user opens the app
- Notifications when the work manager updates the data
- Favorites that persist either outside of the date ranges or just indicators for an exclusive list
- Change from DEMO_KEY to a real key, and abstract and hide it properly - also resource the endpoints