Feature: Login Feature

Scenario: verify login functionality
  Given login page should be open in default browser
  When click on username field and add valid user username
  And then click on password button and enter valid password
  And now click on submit button
  Then login successfully and redirect to home page