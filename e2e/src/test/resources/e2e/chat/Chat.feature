
Feature: Chatbox messaging

	Background:
		Given the user is on "http://localhost:5173"
		And another user is on "http://localhost:5173"

	Scenario Outline: The user can view messages
		Given the chatbox is prepared
		When the another user sends <message>
		Then <message> should display in the chatbox

		Examples:
			| message    					|
			| "hello!"  	 				|
			| "i'm a secondary test user" 	|
			| "i like ace dinosaur" 		|

	Scenario Outline: The user can send messages
		Given the user types <message> in the chatbox input
		When the user submits
		Then <message> should display in the chatbox

		Examples:
			| message    					|
			| "hi!"  	 					|
			| "i'm the primary test user" 	|
			| "i hate ace dinosaur" 		|

	Scenario: The user cannot send empty messages
		Given the user types "" in the chatbox input
		When the user submits
		Then "" should not display in the chatbox
