# Aquent Crud App Code Test

This is a code test for Aquent. Most requirements are minimally met, but not all forms are formatted and only one form (client edit) has client side validation.

## TODO

Further development on this app is not anticipated, but here are the things I would do next if this were a real application.

- Add unit tests on the service objects with JUnit
- Add integration tests with a framework like Selenium; especially test edge cases on form submissions - very little manual testing has been done
- Add form validation to client create, person create, and person edit; use a service to validate addresses
- Add form formatting to client create and person create
- Add persistence management; right now everything is kept in memory and never stored; hsqldb probably is not a good choice for a production app anyway
- Add authentication - right now anyone can modify the database
- Refactor duplicate code, especially in views
