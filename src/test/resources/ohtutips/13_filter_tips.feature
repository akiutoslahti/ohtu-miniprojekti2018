Feature: as a user, I can filter the lists of tips

    Scenario: all tips are shown initially
        Given application has been opened
        Then list of 'book' tips has 3 entries
        And list of 'blog' tips has 3 entries
        And list of 'tube' tips has 3 entries

    Scenario: applying a filter only shows intended content
        Given application has been opened
        When filter 'b' is applied
        Then list of 'book' tips has 2 entries
        And list of 'blog' tips has 2 entries
        And list of 'tube' tips has 3 entries
        And all tips contain 'b'

    Scenario: applying a non-matching filter shows no content
        Given application has been opened
        When filter 'bb' is applied
        Then list of 'book' tips has 0 entries
        And list of 'blog' tips has 0 entries
        And list of 'tube' tips has 0 entries

    Scenario: removing a non-matching filter shows all content
        Given application has been opened
        And filter 'bb' is applied
        When 2 last characters from filter are removed
        Then list of 'book' tips has 3 entries
        And list of 'blog' tips has 3 entries
        And list of 'tube' tips has 3 entries
