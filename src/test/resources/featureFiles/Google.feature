Feature: Google Search Functionality

  @GoogleSearch
  Scenario Outline: Perform Google search and verify page navigation
    Given I navigate to Google search page
    When I check page title
    Then I have page title assertion
    When I query the search bar for "<query>"
    Then I should be able to navigate to the Web page

    Examples:
    |query|
    |Porsche|
    |Audi|
    |Tesla|
    |Ferrari|


