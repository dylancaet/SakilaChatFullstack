
Feature: Create Film Entry

	Background:
		Given the user is on "http://localhost:5173/admin"

	Scenario Outline: Create film with all form entries
		When the user enters a <title> <description> <price> in the create film form
		And the user submits the create film form
		Then the film operation response displays "Film created!"
		And the created film card appears
		Examples:
			| title 					| description 						| price
			| A successful test film 	| A successful test description 	| 11.99

	Scenario Outline: Do not create film with missing form entries
		When the user enters a <title> <description> in the create film form
		And the user submits the create film form
		Then the film operation response displays "Film NOT created!"
		Examples:
			| title 				| description
			| A missing test film 	| A missing test description

	Scenario Outline: Do not create film with invalid form entries
		When the user enters a <title> <description> <price> in the create film form
		And the user submits the create film form
		Then the film operation response displays "Film NOT created!"
		Examples:
			| title 				| description 					| price
			| An invalid test film 	| An invalid test description 	| -1