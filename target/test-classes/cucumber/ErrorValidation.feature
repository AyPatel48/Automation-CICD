
@tag
Feature: Error Validation
  I want to use this template for my feature file

  @ErrorValidations
  Scenario Outline: Title of your scenario outline
    Given I landed on Ecommerce page
    When Logged in with username <name> and password <password>
    Then Verify the message "Incorrect email or password."

    Examples: 
      | name  | value | status  |
      | name1 |     5 | success |
      | name2 |     7 | Fail    |
