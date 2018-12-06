Feature: as a user, i can add a blog tip to the system.

    Scenario: add a new blog tip is successful when all necessary information is provided 
        Given application has been opened
        And button add new reading tip has been clicked
        And 'AddBlog' button has been clicked
        When all necessary 'blog' tip fields have been filled
        And 'Submit' button has been clicked
        Then list of 'blog' tips has 4 entries
        And one of 'blogs' is the newly created one

    Scenario: adding a new blog tip is unsuccessful when all necessary information has not been provided
        Given application has been opened
        And button add new reading tip has been clicked
        And 'AddBlog' button has been clicked
        When all necessary 'blog' tip fields have not been filled
        And 'Submit' button has been clicked
        Then error message 'URL should not be empty' is shown

