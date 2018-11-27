Feature: As a user, I can modify any existing tip.
    
    Scenario: Book tips can be modified
        Given user has opened the application
        When user navigates to any book tip details
        And clicks Edit button
        And enters valid book title
        And clicks Save button
        Then changed book title is shown

    Scenario: Invalid modifications are prevented
        Given user has opened the application
        When user navigates to any book tip details
        And clicks Edit button
        And empties title field
        And clicks Save button
        Then error message "Please do not empty fields marked with (*)." is shown
