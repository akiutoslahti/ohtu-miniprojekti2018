Feature: as a user, i can see all book tips in the system

    Scenario: existing book tips are shown
        Given application has been opened
        Then list of 'book' tips is shown
        And list of 'book' tips has 3 entries
