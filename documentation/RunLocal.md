## How to run application locally

### Requirements

* Java version 8
* Git

### Howto

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

3. Run locally
```
./gradlew bootRun
```
Now application should be accessible by browser in [http://localhost:8080/](http://localhost:8080/)  
*Note: Application uses in-memory database when ran locally. Therefore database is reset every time application is restarted.*
