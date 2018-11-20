Feature: Book tips can be added and are shown in the application

	Scenario: no book tips are shown to user
	Given     application is opened
	When      no book tips in database
	Then      list of book tips is empty

	Scenario: one book tip is added to database
	Given     application is opened
	When      no book tips in database
	And       book title "The Martian" author "Andy Weir" type "Book" isbn "978-0091956134" tags "Science Fiction" is added
	Then      list of book tips has 1 entry

 	Scenario: two book tips are added to database
	Given     application is opened
	When      no book tips in database
	And       book title "The Martian" author "Andy Weir" type "Book" isbn "978-0091956134" tags "Science Fiction" is added
	And       book title "Ready Player One" author "Ernest Cline" type "Book" isbn "978-0099560432" tags "Science Fiction" is added
	Then      list of book tips has 2 entries

