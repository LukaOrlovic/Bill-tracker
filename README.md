# Bill tracker

Bill tracker is a bill tracking app on a large scale (companies). You can manage all your expenses (VATs, salaries and bills) and see how that reflects onto your monthly budget. 

## Prerequisites

You will need XAMPP installed and MySql running and [ElasticSearch][5] running.

[1]: https://spring.io/projects/spring-data-jpa
[2]: https://mvnrepository.com/artifact/javax.validation/validation-api
[3]: https://spring.io/projects/spring-data-elasticsearch
[4]: https://flywaydb.org/
[5]: https://www.elastic.co/
[6]: https://www.amazon.com/Spring-Action-Covers-4/dp/161729120X

## Setup

##### Here are the steps to run the application: #####

1. Login to MySql and create database called "billTracker"
   
        CREATE DATABASE billTracker DEFAULT CHARACTER SET utf8 DEFAULT COLLATE utf8_general_ci;
2. Make sure MySql and ElasticSearch are running

3. Start the application

    
## Libraries

* [Spring Data JPA][1] : makes it easier to build Spring-powered applications that use data access technologies
* [Validation-API][2] : bean Validation API
* [Spring Data Elasticsearch][3] : provides integration with the Elasticsearch search engine
* [Flyway Core][4] : library for database migration and schema management

    
##References
  * [Spring in action][6]

