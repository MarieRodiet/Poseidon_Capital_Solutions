# Poseidon
## Instructions on how to start the app

### Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

### Clone Repository
```
git clone https://github.com/MarieRodiet/Poseidon_Capital_Solutions.git
```

### Create and Run poseidon database
1. In MySQL Workbench, create a new connection (see application.properties - spring.datasource - for username and password)
2. test the connection
3. run the commands localed in file src/main/resources/data.sql
4. query the Users table to see the two users already created from the data.sql file:
```
   select * from db_user;
```

### Start the App in the IDE
```
$ mvn spring-boot:run
```

### Visit the app on http://localhost:8080/

After login with User (username), user (password), navigate through the app
ex: http://localhost:8080/ruleName/list

