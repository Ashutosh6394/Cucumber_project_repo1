Feature: TutorialNinja E-Commerce Automation

@Smoke
Scenario: Open website
Given user opens tutorialninja website

@Smoke
Scenario: Register a new user
When user registers new account
Then account should be created successfully

@Smoke
Scenario: Login with valid credentials
When user login with valid details
Then login should be successful

@Regression
Scenario Outline: Search multiple products
When user searches product "<product>"
Then search result should be displayed

Examples:
| product |
| iphone  |
| macbook |
| camera  |

@Regression
Scenario: Add product to cart
When user adds product to cart
Then product should be added successfully

@Regression
Scenario: Delete product from cart
When user deletes product from cart
Then cart should be empty

@Regression
Scenario: Change address
When user changes address
Then address should be changed successfully

@Regression
Scenario: Change account name
When user changes name
Then name should be changed successfully

@Smoke
Scenario: Close browser
Then user quits browser