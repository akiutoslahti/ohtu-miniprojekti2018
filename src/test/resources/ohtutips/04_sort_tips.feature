Feature: as a user, I can sort tips by author or title

Scenario: book tips are initially sorted by id
    Given user has opened the application
    And list of book tips is shown
    Then book tips are sorted by id

Scenario: book tips can be sorted by author
    Given user has opened the application
    And list of book tips is shown
    When 'sort by author' is clicked
    Then book tips are sorted by author

Scenario: book tips can be sorted by title
    Given user has opened the application
    And list of book tips is shown
    When 'sort by title' is clicked
    Then book tips are sorted by title

Scenario: book tips can be sorted by id
    Given user has opened the application
    And list of book tips is shown
    And 'sort by title' is clicked
    When 'sort by id' is clicked
    Then book tips are sorted by id
