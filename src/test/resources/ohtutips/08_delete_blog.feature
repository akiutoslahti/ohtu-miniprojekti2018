Feature: as a user, I can remove any blog tip

    Scenario: blog tip can be deleted
        Given application has been opened
        And list of 'blog' tips is shown
        And list of 'blog' tips has 4 entries
        When user navigates to 'blog' tip details
        And 'Delete' button has been clicked
        Then list of 'blog' tips is shown 
        And list of 'blog' tips has 3 entries
        And deleted 'blog' is not listed

