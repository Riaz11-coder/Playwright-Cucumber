@GoogleSearch
Feature: Google Search Functionality


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

  Scenario: Perform Google search and verify page navigation
    Given I open the Google homepage
    When I search for "Playwright Cucumber"
    Then I should see search results for "Playwright Cucumber"

  Scenario: Search for a keyword and verify title contains keyword
    Given I open the Google homepage
    When I search for "JUnit 5 parallel execution"
    Then the page title should contain "JUnit 5 parallel execution"

  Scenario: Search for an image keyword and verify image results
    Given I open the Google homepage
    When I search for "Puppeteer vs Playwright"
    And I navigate to image search
    Then I should see image results for "Puppeteer vs Playwright"
