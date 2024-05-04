# Improvement Ideas

- Consider combining NASA services - project uses separate modules for demonstration purposes
- Add parameters to allow for different dates or date ranges
- Strings resources and localization
- Better failure or offline handling - use error results instead of just null values
- Accessibility improvements
- Kotlin/Wasm to host documentation through GitHub pages via workflow
- Architectural testing using konsist
- Add a work manager to schedule the updates so data is always up to date when user opens the app
- Notifications when the work manager updates the data
- Favorites that persist either outside of the date ranges or just indicators for an exclusive list
- Change from DEMO_KEY to a real key, and abstract and hide it properly - also resource the
  endpoints
- Expand testing, cover more edge cases, and add more unit tests
- Dimens for padding values instead of hardcoding values all over
- Automated versioning, release workflow, release notes and tagging

TODO for finishing short project

- Create ui for neo detail screen
- Create shared ui components between apod and neo details
- Add unit tests and ui tests for app