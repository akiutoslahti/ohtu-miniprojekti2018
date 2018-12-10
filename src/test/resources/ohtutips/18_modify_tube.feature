Feature: As a user, I can modify any existing youtube tipe.
    
    Scenario: Youtube tips can be modified
        Given application has been opened
        When any 'tube' tip is navigated to
        And 'Edit' button has been clicked
        And valid 'tube' title has been entered
        And 'Save' button has been clicked
        Then changed 'tube' title is shown

    Scenario: Invalid modifications are prevented
        Given application has been opened
        When any 'tube' tip is navigated to
        And 'Edit' button has been clicked
        And title field has been emptied
        And 'Save' button has been clicked
        Then error message 'Title should not be empty' is shown

