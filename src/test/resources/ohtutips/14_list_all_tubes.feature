Feature: as a user, i can see all youtube tips in the system

    Scenario: existing youtube tips are shown
        Given application has been opened
        Then list of 'tube' tips is shown
        And list of 'tube' tips has 3 entries
