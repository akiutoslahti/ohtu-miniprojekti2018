Feature: as a user, i can add a book tip to the system.

    Scenario: add a new book tip is successful when all necessary information is provided 
        Given application has been opened
        And button add new reading tip has been clicked
        And 'AddBook' button has been clicked
        When all necessary 'book' tip fields have been filled
        And 'Submit' button has been clicked
        Then list of 'book' tips has 4 entries
        And one tip of type 'book' is the newly created one

    Scenario: adding a new book tip is unsuccessful when all necessary information has not been provided
        Given application has been opened
        And button add new reading tip has been clicked
        And 'AddBook' button has been clicked
        When all necessary 'book' tip fields have not been filled
        And 'Submit' button has been clicked
        Then error message 'ISBN should not be less than 10 characters or more than 14 characters' is shown
