Feature: Login

Scenario: User Login
Given Valid Username and Password that has been registered
When User enters username and password 
And Clicks login
Then Page is redirected to Get Started Page
