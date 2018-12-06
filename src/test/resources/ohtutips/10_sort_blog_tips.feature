Feature: as a user, I can sort blog tips by author or title

    Scenario: blog tips are initially sorted by id
        Given application has been opened
        And list of 'blog' tips is shown
        Then 'blog' tips are sorted by id

    Scenario: blog tips can be sorted by author
        Given application has been opened
        And list of 'blog' tips is shown
        When sort by 'author' is clicked
        Then 'blog' tips are sorted by 'author'
#       Then blog tips are sorted by author

    Scenario: blog tips can be sorted by title
        Given application has been opened
        And list of 'blog' tips is shown
        When sort by 'title' is clicked
        Then 'blog' tips are sorted by 'title'
#       Then blog tips are sorted by title

    Scenario: blog tips can be sorted by id
        Given application has been opened
        And list of 'blog' tips is shown
        And sort by 'title' is clicked
        When sort by 'id' is clicked
        Then 'blog' tips are sorted by id
