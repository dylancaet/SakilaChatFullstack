
Feature: Delete Film Entry

	Background:
		Given the user is on "http://localhost:5173/admin"
		And the user can see the latest film id

	Scenario: Delete latest film
		When the user enters the latest id in the delete film form
		And the user submits the delete film form
		Then the film operation response displays "Film deleted!"