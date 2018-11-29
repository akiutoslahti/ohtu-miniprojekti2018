Feature: as a user, i can see all blog tips in the system

    Scenario: existing blog tips are shown
        Given application has been opened
        Then list of 'blog' tips is shown
        And list of 'blog' tips has 3 entries
