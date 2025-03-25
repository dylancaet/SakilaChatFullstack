
Feature: Create Film Entry

	Background:
		Given the user is on "http://localhost:5173/admin"

	Scenario: Create film with all form entries
		When the user enters "A successful test film" "A successful test description" 11.99 in the create film form
		And the user submits the create film form
		Then the film operation response displays "Film created!"
		And the created film card appears

	Scenario: Do not create film with missing form entries
		When the user enters "A missing test film" "A missing test description" in the create film form
		And the user submits the create film form
		Then the film operation response displays "Film NOT created!"

	Scenario: Do not create film with invalid form entries
		When the user enters "An invalid test film" "An invalid test description" -1 in the create film form
		And the user submits the create film form
		Then the film operation response displays "Film NOT created!"