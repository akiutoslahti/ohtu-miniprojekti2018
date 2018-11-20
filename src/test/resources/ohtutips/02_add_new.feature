Feature: as a user, i can add a book tip to the system.

    Scenario: add a new book tip is successful when all necessary information is provided 
        Given user has opened the application and link 'make a new book tip' has been clicked
        When all necessary book tip fields have been filled
        And book tip has been submitted
        Then list of book tips has 4 entries
        And one of them is the newly created one

    Scenario: adding a new book tip is unsuccessful when all necessary information has not been provided
        Given user has opened the application and link 'make a new book tip' has been clicked
        When all necessary book tip fields have not been filled
        And book tip has been submitted
        Then error message "Please fill all fields marked with (*)." is shown
