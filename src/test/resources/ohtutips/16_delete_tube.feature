Feature: as a user, I can remove any youtube tip

    Scenario: youtube tip can be deleted
        Given application has been opened
        And list of 'tube' tips is shown
        And list of 'tube' tips has 4 entries
        When user navigates to 'tube' tip details
        And 'Delete' button has been clicked
        Then list of 'tube' tips is shown 
        And list of 'tube' tips has 3 entries
        And deleted 'tube' is not listed

