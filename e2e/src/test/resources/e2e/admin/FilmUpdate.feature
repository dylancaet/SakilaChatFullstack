
Feature: Update Film Entry

	Background:
		Given the user is on "http://localhost:5173/admin"
		And the user can see the latest film id

	Scenario Outline: Update latest film with all form entries
		When the user enters the latest id <title> <description> <price> in the update film form
		And the user submits the update film form
		Then the film operation response displays "Film updated!"
		And the updated film card appears
		Examples:
			| title 				| description 					| price |
			| An updated test film 	| An updated test description 	| 5.99 |


	Scenario Outline: Do not update film with invalid form entries
		When the user enters the latest id <title> <description> <price> in the update film form
		And the user submits the update film form
		Then the film operation response displays "Film NOT updated!"
		Examples:
			| title 						| description 							| price |
			| An invalid updated test film 	| An invalid updated test description 	| -1 |