Feature: As a user, I can modify any existing blog.
    
    Scenario: Blog tips can be modified
        Given application has been opened
        When any 'blog' tip is navigated to
        And 'Edit' button has been clicked
        And valid 'blog' title has been entered
        And 'Save' button has been clicked
        Then changed 'blog' title is shown

    Scenario: Invalid modifications are prevented
        Given application has been opened
        When any 'blog' tip is navigated to
        And 'Edit' button has been clicked
        And title field has been emptied
        And 'Save' button has been clicked
        Then error message 'Title should not be empty' is shown

