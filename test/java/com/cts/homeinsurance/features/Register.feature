Feature: Register User

# User creates a user account
Scenario: User Creates a User account
Given UserName and Password, and User is on the registration page
When User enters username and password 
And Clicks register
Then Page is redirected to the Login Page