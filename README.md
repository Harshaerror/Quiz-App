Microservices Project
This project is a Spring Boot-based Microservices Architecture that consists of multiple services working together to provide a complete application.
Each microservice is an independent, deployable application focused on a single business capability, using REST APIs to communicate.

Table of Contents
Project Structure
Technologies
Getting Started
Microservices
API Gateway
Database Setup
Running the Project
Testing

Project Structure
This project follows a modular microservices architecture. Each microservice is designed for a specific functionality:

Service Registry (Eureka Server)
API Gateway (Zuul/Gateway)
Service 1: Example: User Service
Service 2: Example: Quiz Service
Service 3: Example: Question Service
Each microservice communicates over HTTP via REST APIs. The API Gateway routes requests to the appropriate services, while the Service Registry maintains the locations and availability of each microservice.

Technologies
Java - 17 or later
Spring Boot - Core framework for building microservices
Spring Cloud - Eureka, API Gateway, Config Server, etc.
MySQL - Database
Docker (optional) - For containerization
Postman (for testing API endpoints)
Getting Started
Prerequisites
Java (17 or later)
MySQL - Set up with relevant databases
Maven - For building the application
Docker (optional) - For running containers
Installation
Clone the repository.
bash
Copy code
git clone https://github.com/yourusername/microservices-project.git
Open each service in Visual Studio Code or IntelliJ IDEA as separate modules.
Microservices
Service Registry
Description: This service allows other services to register and discover each other.
Run Command: mvn spring-boot:run from the service-registry directory.
API Gateway
Description: Handles incoming requests and forwards them to appropriate services.
Run Command: mvn spring-boot:run from the api-gateway directory.
User Service
Description: Manages users within the system.
Endpoints:
POST /user/add
GET /user/{id}
Port: 8081
Quiz Service
Description: Manages quizzes and associated metadata.
Endpoints:
POST /quiz/create
GET /quiz/{id}
Port: 8082
Question Service
Description: Handles questions used in quizzes.
Endpoints:
POST /question/add
GET /question/{id}
Port: 8083
Database Setup
Create a database for each microservice.
Update the application.properties file for each microservice with the correct database credentials.
Example for User Service:

properties
Copy code
spring.datasource.url=jdbc:mysql://localhost:3306/user_service
spring.datasource.username=root
spring.datasource.password=your_password
Running the Project
Start the Service Registry (Eureka) - Run ServiceRegistryApplication.
Start the API Gateway - Run ApiGatewayApplication.
Start Each Service:
Open separate terminals and run each service.
Example: mvn spring-boot:run from each service's directory.
Testing
Use Postman or Swagger UI (if enabled) to test each endpoint.
API Gateway endpoints are accessible via http://localhost:8080.
Contributing
Feel free to submit issues or contribute to this project by forking and submitting a pull request.
