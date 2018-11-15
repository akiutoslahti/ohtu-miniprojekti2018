## Continuous integration and build

### Travis
Project uses [Travis](https://travis-ci.org/akiutoslahti/ohtu-miniprojekti2018) continuous integration for purpose of: 
* code coverage reports in [codecov](https://codecov.io/gh/akiutoslahti/ohtu-miniprojekti2018)
* automatic deployment to [Heroku](https://ohtutips.herokuapp.com/)

### Note on continuous integration
* Travis follows the master branch of repository and every commit to master triggers a build in Travis.
	* Test reports are always submitted to codecov.
	* Every succesfull build triggers a deploy to Heroku.
		* Only push tested features to master branch in order to ensure that Heroku demo represents latest working state of application.
