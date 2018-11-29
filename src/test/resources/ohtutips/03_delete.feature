Feature: as a user, I can remove any existing tip

    Scenario: book tip can be deleted
        Given application has been opened
        And list of book tips is shown
        And list of book tips has 4 entries
        When user navigates to book tip details
        And 'Delete' button has been clicked
        Then list of book tips is shown 
        And list of book tips has 3 entries
        And deleted one is not listed
