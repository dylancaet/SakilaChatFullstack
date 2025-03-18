
Feature: View more results

	Background:
		Given the user is on "http://ec2-3-84-235-208.compute-1.amazonaws.com"

	Scenario: The page initially loads 50 items
		Then 50 film cards are displayed

	Scenario: A user can load more items
		When the user clicks the load more button
		Then more or the same film cards are displayed

#	Scenario: A user can load more results when filtering