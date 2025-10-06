Feature: login test

Scenario Outline: To verify login with different username and password
Given user is on login page
When user enters <user> and <pass>
And click on login button
Then login successfull page is displayed and verify "https://www.saucedemo.com/inventory.html"
And successfull messgae "login success"

Examples:
|user					|pass			|
|standard_user			|secret_sauce	| 
|problem_user			|secret_sauce	| 
|locked_out_user		|secret_sauce	|	
|performance_glitch_user|secret_sauce	|
|error_user				|secret_sauce	|
|visual_user			|secret_sauce	| 


