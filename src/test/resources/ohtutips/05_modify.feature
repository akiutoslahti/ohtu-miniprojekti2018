Feature: As a user, I can modify any existing tip.
    
    Scenario: Book tips can be modified
        Given application has been opened
        When any book tip is navigated to
        And 'Edit' button has been clicked
        And valid title has been entered
        And 'Save' button has been clicked
        Then changed book title is shown

    Scenario: Invalid modifications are prevented
        Given application has been opened
        When any book tip is navigated to
        And 'Edit' button has been clicked
        And title field has been emptied
        And 'Save' button has been clicked
        Then error message 'Please do not empty fields marked with (*).' is shown
