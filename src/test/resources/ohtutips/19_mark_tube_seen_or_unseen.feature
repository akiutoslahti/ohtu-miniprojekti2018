Feature: As a user, I can mark any existing youtube tip seen or unseen.

    Scenario: Unseen youtube videos can be marked as seen on the details page
        Given application has been opened
        When show studied is clicked 
        And list of 'tube' tips is shown
        And 'tube' tip number 1 is 'not' studied
        And 'tube' tip number 1 is navigated to
        And 'tube' tip is 'not' studied
        And 'tube' tip studied is clicked
        And 'tube' tip is 'already' studied
        And 'Back' button has been clicked
        And show studied is clicked
        Then 'tube' tip number 1 is 'already' studied

    Scenario: Seen youtube videos can be marked as unseen on the details page
        Given application has been opened
        When show studied is clicked
        And list of 'tube' tips is shown
        And 'tube' tip number 1 is 'already' studied
        And 'tube' tip number 1 is navigated to
        And 'tube' tip is 'already' studied
        And 'tube' tip studied is clicked
        And 'tube' tip is 'not' studied
        And 'Back' button has been clicked
        And show studied is clicked
        Then 'tube' tip number 1 is 'not' studied