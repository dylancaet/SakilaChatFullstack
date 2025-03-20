
Feature: Search for results

	Background:
		Given the user is on "http://localhost:5173"

	Scenario Outline: The user searches for
		Given the user types <filter> in the filter
		When the user submits the filter
		Then <count> films should display

		Examples:
			| filter     | count |
			| "ace"  	 | 15    |
			| "dinosaur" | 3 	 |
			| "a" 	     | 50    |