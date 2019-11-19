Warehouse API
=======

This project is an example of a tiny REST / JSON web service in Java using Spring Boot (RestController) with an API 
that supports basic CRUD operations:
	* Create a new product
	* Get a list of all products
	* Update a product

The API also support:
	* Placing an order
	* Retrieving all orders within a given time period

A product have a name and some representation of its price.
Each order is recorded and have a list of products. It also have the buyer’s e-mail, and the time the order was placed. The total value of the order are always be calculated, based on the prices of the products in it.
It is possible to change the product’s price, but this does not affect the total value of orders which have already been placed.


# Contents

0. Requirements
1. Installation
	1. Create the docker image
	2. Instantiate a docker container
	3. Run the docker container
	4. Create the warehouseSchema
	5. Build the project
	6. Run the migration scripts
2. Technical questions
  

## Requirements

The application has been coded/build using the following software:
	1- Oracle JDK version: 1.8
	2- Apache Maven 3.5.4
	3- Flyway Community Edition 5.2.4 
	4- Docker 18.09.1
		4.1- MySQL 8.0.18   
	
In order run successfully the application you will need to have installed in your computer all the software mentioned
above (with same version number or superior) from 1 to 4. For the database you will find instructions here on how to
recreate embed the MySQL into a docker container.  
 
## Installation


### Create the docker image

To create a docker image with MySQL 8.0 inside simple execute the following command in the terminal

	sudo docker pull mysql:8.0

Verify that the image has been created by executing the command:

	sudo docker images

You must see an image with REPOSITORY "mysql" and TAG 8.0

### Instantiate a docker container

Now that the image has been pulled from docker hub to instantiate a container from the given image is as easy as executing the next command. This instantiates a container from the image created in the previous step and exposes the ports 3306 and 33060 to the exterior. The instruction also setup a root password for the MySQL instance inside the docker container and creates the user warehouseApi with its own password.

	sudo docker run --name=warehouse-mysql -e MYSQL_ROOT_PASSWORD=root1234 -e MYSQL_DATABASE=warehouseSchema -e MYSQL_USER=warehouseApi -e MYSQL_PASSWORD=warehouseApi1234 -p 3306:3306 -p 33060:33060 -d mysql:8.0

The previous command must create a container with NAME "warehouse-mysql" to see the existing running containers you can execute: 

	sudo docker ps -a

In the list of containers you must see running the container you just instantiated.

### Run the docker container

If you are following this instructions in secuential order you do not need to start the container since it is already started. But if you stopped it, this is the command to start it again: 

	sudo docker start warehouse-mysql

In case you want to attach a terminal to the container, i.e. For accessing to mysql or execute any command inside the container, then execute:

	sudo docker exec -it warehouse-mysql /bin/bash

To stop the container execute the following command:

	sudo docker stop warehouse-mysql



### Create the warehouseSchema

This step is only needed if you skipped the creation of the Docker Container and you use your own instance of MySQL. In that case please be aware that you will need to create the database and the user "warehouseApi" with password "warehouseApi1234" and give them all privileges on the DB warehouseSchema.

	CREATE SCHEMA `warehouseSchema` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;

For creating the users you can have a look [here](https://www.digitalocean.com/community/tutorials/how-to-create-a-new-user-and-grant-permissions-in-mysql)


### Build the project

Before proceed forward you will need to build the application so please go to the directory where you have checked out this repository and:

	mvn clean install


### Run the migration scripts

To recreate the desired state of the DB you will need to execute the migration scripts using [Flyway](https://flywaydb.org/getstarted/why). The first step is to give a baseline to our warehouseSchema so you can execute the following command (IMPORTANT NOTE: In the parameter -jarDirs you will have to change the path to the directory that contains the warehouse-api project in your computer):

	flyway baseline -baselineVersion=000.000 -url=jdbc:mysql://localhost:3306/warehouseSchema?rewriteBatchedStatements=true -user=warehouseApi -password=warehouseApi1234 -jarDirs=/home/francisco/git/Francisco/github/warehouse-api/target  


Verify that your database is in the state 000.000 by executing the info command (IMPORTANT NOTE: In the parameter -jarDirs you will have to change the path to the directory that contains the warehouse-api project in your computer):

	flyway info -url=jdbc:mysql://localhost:3306/warehouseSchema?rewriteBatchedStatements=true -user=warehouseApi -password=warehouseApi1234 -locations=classpath:db.migration -jarDirs=/home/francisco/git/Francisco/github/warehouse-api/target


Now run the migration scripts to update your warehouseSchema to the latest state (IMPORTANT NOTE: In the parameter -jarDirs you will have to change the path to the directory that contains the warehouse-api project in your computer):

	flyway migrate -url=jdbc:mysql://localhost:3306/warehouseSchema?rewriteBatchedStatements=true -user=warehouseApi -password=warehouseApi1234 -locations=classpath:db.migration -jarDirs=/home/francisco/git/Francisco/github/warehouse-api/target

 
## Technical questions


### Q1 - Propose a protocol / method to add authentication to your web service (Justify your choice).

Rather than using authentication I would use authorization and I would propose using some  version of OAuth ( Preferably OAuth 2.0).

OAuth offer multiple advantages, specially when building a microservices environment on which it is expected that the client will consume multipe of them to achieve a goal. In this kind of scenarios the main advantage of authentication with OAuth is the clear separation of resposabilities. There is an OAuth provider responsible of the user authentication and the rest
of the APIs or services they just need to use the same OAuth provider to authorise the client.

As a final user, is also easier to log in one single time in one service (Authentication provider) rather than having to log into multiple different services with different credentials, avoiding the overhead of credentials and enforcing more user-friendly security policies. As a consumer I have seen authorisation multiple times. There are services that allow us to use them if we log in through other services. I.e. Evernote allows you to use their service if you log in into Gmail or LinkedIn. 


### Q2 - How can you make the service redundant? What considerations should you do?

Redundancy and high availability are big topics. To make the service redundant it has to be design and built to operate in a cluster. Regarding RESTful services one of the main concerns is the consistancy of the information when there are concurrent access. To solve this problem, in this example I have implemented an Optimitic Locking strategy. See in the DB the column VERSION and in the code the annotation @Version.





