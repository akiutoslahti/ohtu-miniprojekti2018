Feature: as a user, I can sort book tips by author or title

    Scenario: book tips are initially sorted by id
        Given application has been opened
        And list of 'book' tips is shown
        Then 'book' tips are sorted by id

    Scenario: book tips can be sorted by author
        Given application has been opened
        And list of 'book' tips is shown
        When sort 'books' by 'author' is clicked
        Then book tips are sorted by author

    Scenario: book tips can be sorted by title
        Given application has been opened
        And list of 'book' tips is shown
        When sort 'books' by 'title' is clicked
        Then book tips are sorted by title

    Scenario: book tips can be sorted by id
        Given application has been opened
        And list of 'book' tips is shown
        And sort 'books' by 'title' is clicked
        When sort 'books' by 'id' is clicked
        Then 'book' tips are sorted by id

