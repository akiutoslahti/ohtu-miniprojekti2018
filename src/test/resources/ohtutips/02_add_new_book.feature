Feature: as a user, i can add a book tip to the system.

    Scenario: add a new book tip is successful when all necessary information is provided 
        Given application has been opened
        And link 'add a book tip' has been clicked
        When all necessary 'book' tip fields have been filled
        And 'Submit' button has been clicked
        Then list of 'book' tips has 4 entries
        And one of 'books' is the newly created one

    Scenario: adding a new book tip is unsuccessful when all necessary information has not been provided
        Given application has been opened
        And link 'add a book tip' has been clicked
        When all necessary 'book' tip fields have not been filled
        And 'Submit' button has been clicked
        Then error message 'Please fill all fields marked with (*).' is shown
