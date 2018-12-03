Feature: As a user, I can mark any existing book read or unread.
    
    Scenario: Unread books can be marked as read on the main page
        Given application has been opened
        When list of 'book' tips is shown
        And 'book' tip number 2 is 'not' studied
        And 'book' tip number 2 studied is clicked
        And 'book' tip number 2 is 'already' studied
        And 'book' tip number 2 is navigated to
        Then 'book' tip is 'already' studied

    Scenario: Read books can be marked as unread on the details page
        Given application has been opened
        When list of 'book' tips is shown
        And 'book' tip number 2 is 'already' studied
        And 'book' tip number 2 is navigated to
        And 'book' tip is 'already' studied
        And 'book' tip studied is clicked
        And 'book' tip is 'not' studied
        And 'Back' button has been clicked
        Then 'book' tip number 2 is 'not' studied

    Scenario: Unread books can be marked as read on the details page
        Given application has been opened
        When list of 'book' tips is shown
        And 'book' tip number 1 is 'not' studied
        And 'book' tip number 1 is navigated to
        And 'book' tip is 'not' studied
        And 'book' tip studied is clicked
        And 'book' tip is 'already' studied
        And 'Back' button has been clicked
        Then 'book' tip number 1 is 'already' studied

    Scenario: Read books can be marked as unread on the main page
        Given application has been opened
        When list of 'book' tips is shown
        And 'book' tip number 1 is 'already' studied
        And 'book' tip number 1 studied is clicked
        And 'book' tip number 1 is 'not' studied
        And 'book' tip number 1 is navigated to
        Then 'book' tip is 'not' studied
