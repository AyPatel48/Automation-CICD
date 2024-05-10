
@tag
Feature: Purchase the order from Ecommerce website
  I want to use this template for my feature file

	Background:
	Given I landed on the Ecommerce Page
	
  @Regression
  Scenario Outline: Positive test of Submitting the order
    Given I logged in with username <name> and password <password>
    When I add product <productName> to cart
    And checkout <productName> and submit the order
    Then Confirmation message is verified "THANK YOU FOR THE ORDER"

    Examples: 
      | name  											 | password		 | productName |
      | rahulshettyacademy@gmail.com | IamKing@000 | ZARA COAT 3 |
      
