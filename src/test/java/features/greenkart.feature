Feature:Greenkart

Scenario: To verify end to end checkout jounry of vegitable.
Given user is on greenkart site 
When user add vegitable in basket
Then perform chekout
And Successfull message should display.
	