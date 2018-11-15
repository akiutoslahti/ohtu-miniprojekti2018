## How to setup development environment

### Requirements

* Java version 8
* Gradle version 4.4 or greater (use gradle wrapper provided in repository)
* [LiveReload](https://chrome.google.com/webstore/detail/livereload/jnihajbhpnppcggbcgedagnkighmdlei) browser plugin

### How to set environment

1. Clone repository and change directory
```
git clone https://github.com/akiutoslahti/ohtu-miniprojekti2018
cd ohtu-miniprojekti2018
```

2. Ensure gradle wrapper is working
```
./gradlew --version
```
Output should indicate ```Gradle 4.8.1```

3. Ensure environment is working
```
./gradlew bootRun
```
Now application should be accessible by browser in [http://localhost:8080/](http://localhost:8080/)

### Best practices on how to develop

1. Open a terminal and navigate to your project folder
2. Start continuous building
```
./gradlew build --continuous
```
3. Open another terminal and navigate to your project folder
4. Start application
```
./gradlew bootRun
```
5. Navigate in browser to [http://localhost:8080/](http://localhost:8080/) and enable LiveReload extension from Chrome extension bar.  

Now your browser should show the application in state it is currently. As you make any modification to project a new build is triggered automatically and in couple of seconds your browser should reload the new state of application.

### Other useful commands

Running tests  
```
./gradlew test
```

Cleaning previous build  
```
./gradlew clean
```