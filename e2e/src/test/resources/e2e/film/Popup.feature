
Feature: View more film information

	Background:
		Given the user is on "http://localhost:5173"

	Scenario: A user can view more film information
		When the user clicks the first film card
		Then a popup of the film card displays