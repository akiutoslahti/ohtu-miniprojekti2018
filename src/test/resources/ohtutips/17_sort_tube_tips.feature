Feature: as a user, I can sort youtube tips by author or title

    Scenario: youtube tips are initially sorted by id
        Given application has been opened
        And list of 'tube' tips is shown
        Then 'tube' tips are sorted by id

    Scenario: youtube tips can be sorted by author
        Given application has been opened
        And list of 'tube' tips is shown
        When sort by 'author' is clicked
        Then 'tube' tips are sorted by 'author'

    Scenario: youtube tips can be sorted by title
        Given application has been opened
        And list of 'tube' tips is shown
        When sort by 'title' is clicked
        Then 'tube' tips are sorted by 'title'

    Scenario: youtube tips can be sorted by id
        Given application has been opened
        And list of 'tube' tips is shown
        And sort by 'title' is clicked
        When sort by 'id' is clicked
        Then 'tube' tips are sorted by id
