# Ranking Service #

Create a simple project to retrieve a list of rankings.

### What does this project use? ###

* [Lombok](https://projectlombok.org/) To remove boilerplate code
* [Docker](https://www.docker.com/) For Containers
* [MockServer](https://www.mock-server.com/) For Mocking external apps interaction
* [Test Containers](https://www.testcontainers.org/) To Start local containers for tests
* [PostgreSQL](https://www.postgresql.org/)

### Running as a Spring Boot Application ###
* Spin up a PostgreSQL instance, with the same credentials and db name as defined in config/application.yml.
* Launch the **RankingServiceApplication.class**. 
* In main/resources/runLocally there are examples to spin up a DB instance alone or with the application.


### Testing ###
* Uses *Test Containers* for end-to-end testing  and *Mock Server* for integration testing.


### Comments ###
* Entity classes have been used on the service layer as the example was very straight forward. For more complex ones it can be a good idea to have model classes.