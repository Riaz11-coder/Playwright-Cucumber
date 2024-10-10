Feature: Google test
  @Google
  Scenario: Testing Google
    Given I navigate to Google search page
    When  I check page title
    Then  I have page title assertion
  @GoogleSearch
    Scenario: Searching Google
      Given I navigate to Google search page
      When  I query the search bar
      Then  I should be able to navigate to the Web page