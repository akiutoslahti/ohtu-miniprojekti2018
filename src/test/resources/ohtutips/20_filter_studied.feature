Feature: As a user, I only see unread tips, but can also choose to see all tips.

    Scenario: Read books are not listed by default
        Given application has been opened
        And list of 'book' tips is shown
        And list of 'book' tips has 3 entries
        And 'book' tip number 1 is navigated to
        And 'book' tip studied is clicked
        And 'Back' button has been clicked
        Then list of 'book' tips has 2 entries

    Scenario: Read books can also be shown
        Given application has been opened
        And list of 'book' tips is shown
        And list of 'book' tips has 2 entries
        And show studied is clicked
        Then list of 'book' tips has 3 entries

    Scenario: Read blogs are not listed by default
        Given application has been opened
        And list of 'blog' tips is shown
        And list of 'blog' tips has 3 entries
        And 'blog' tip number 1 is navigated to
        And 'blog' tip studied is clicked
        And 'Back' button has been clicked
        Then list of 'blog' tips has 2 entries

    Scenario: Read blogs can also be shown
        Given application has been opened
        And list of 'blog' tips is shown
        And list of 'blog' tips has 2 entries
        And show studied is clicked
        Then list of 'blog' tips has 3 entries

    Scenario: Seen youtube videos are not listed by default
        Given application has been opened
        And list of 'tube' tips is shown
        And list of 'tube' tips has 3 entries
        And 'tube' tip number 1 is navigated to
        And 'tube' tip studied is clicked
        And 'Back' button has been clicked
        Then list of 'tube' tips has 2 entries

    Scenario: Seen youtube videos can also be shown
        Given application has been opened
        And list of 'tube' tips is shown
        And list of 'tube' tips has 2 entries
        And show studied is clicked
        Then list of 'tube' tips has 3 entries