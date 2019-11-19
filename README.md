TODO:

	0- Considerations
	1- Questions
	2- Unit Tests   --> https://www.callicoder.com/hibernate-spring-boot-jpa-one-to-many-mapping-example/
	3- Document REST-API
	4- Provide a storage solution for persisting the web service's state
	5- Have a way to run the service with its dependencies locally (Script, docker, or something else).
	6- Add technical documentation
	6- Add the loggers

/*****************************************************************************************/




/*****************************************************************************************/

CREATE THE CONTAINER

sudo docker pull mysql:8.0

sudo docker images

sudo docker run --name=warehouse-mysql -e MYSQL_ROOT_PASSWORD=root1234 -e MYSQL_DATABASE=warehouseSchema -e MYSQL_USER=warehouseApi -e MYSQL_PASSWORD=warehouseApi1234 -p 3306:3306 -p 33060:33060 -d mysql:8.0

sudo docker ps -a

sudo docker exec -it warehouse-mysql /bin/bash


The image was pulled from docker: https://hub.docker.com/_/mysql

/*****************************************************************************************/
START / STOP THE CONTAINER

sudo docker start -ai warehouse-mysql


sudo docker stop warehouse-mysql


/*****************************************************************************************/
This step is only needed if you skipped the creation of the Docker Container and you use the DB in
a different instance. In that case please be aware that you will need to create the database and the
user "warehouseApi" with password "warehouseApi1234" and give them all privileges on the DB warehouseSchema.


CREATE SCHEMA `warehouseSchema` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;


For creating the users you can have a look here: https://www.digitalocean.com/community/tutorials/how-to-create-a-new-user-and-grant-permissions-in-mysql

/*****************************************************************************************/

flyway baseline -baselineVersion=000.000 -url=jdbc:mysql://localhost:3306/warehouseSchema?rewriteBatchedStatements=true -user=warehouseApi -password=warehouseApi1234 -jarDirs=/home/francisco/git/Francisco/github/warehouse-api/target  

flyway info -url=jdbc:mysql://localhost:3306/warehouseSchema?rewriteBatchedStatements=true -user=warehouseApi -password=warehouseApi1234 -locations=classpath:db.migration -jarDirs=/home/francisco/git/Francisco/github/warehouse-api/target

flyway migrate -url=jdbc:mysql://localhost:3306/warehouseSchema?rewriteBatchedStatements=true -user=warehouseApi -password=warehouseApi1234 -locations=classpath:db.migration -jarDirs=/home/francisco/git/Francisco/github/warehouse-api/target


/*****************************************************************************************/

To build the project:
	cd /home/francisco/git/Francisco/github/warehouse-api
	mvn clean package
	
	
To run the project:	
	java -jar target/gs-rest-service-0.1.0.jar
	
	
/*****************************************************************************************/
To consume the service:
	http://localhost:8080/greeting
	http://localhost:8080/greeting?name=YourName
