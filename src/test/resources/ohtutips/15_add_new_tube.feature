Feature: as a user, i can add a youtube tip to the system.

    Scenario: add a new youtube tip is successful when all necessary information is provided 
        Given application has been opened
        And button add new reading tip has been clicked
        And 'AddTube' button has been clicked
        When all necessary 'tube' tip fields have been filled
        And 'Submit' button has been clicked
        Then list of 'tube' tips has 4 entries
        And one tip of type 'tube' is the newly created one

    Scenario: adding a new youtube tip is unsuccessful when all necessary information has not been provided
        Given application has been opened
        And button add new reading tip has been clicked
        And 'AddTube' button has been clicked
        When all necessary 'tube' tip fields have not been filled
        And 'Submit' button has been clicked
        Then error message 'URL should not be empty' is shown

