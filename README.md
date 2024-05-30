# MicroStamp Step 2

MicroSTAMP Step 2 - a Spring boot microservice for Step 2 of STPA.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)

## Steps to Execute

1. **Clone Project**: Clone this repository to your local machine.
2. **Configure `application.properties`**: Set up your SQL configurations in the `application.properties` file located
   in the `src/main/resources` directory. By default, it assumes port 3306 and "step2" as the database name. Ensure to
   provide the correct URL, port, username, and password for your SQL database.
3. **Create SQL Database**: Create the required SQL database as configured in the `application.properties` file.
4. **Run the Project**:
    - Open a terminal.
    - Navigate to the root directory of the project.
    - Execute the following commands:
        ```
        mvnw compile
        mvnw spring-boot:run
        ```

## Access Points

Once the project is running, you can access the following endpoints:

- **API Documentation**: [http://localhost:8090/swagger](http://localhost:8090/swagger) - Swagger API documentation.
- **Guest Visualization**: [http://localhost:8090/guests](http://localhost:8090/guests) - Visualization for guests, with
  some example projects.
- **Login Page**: [http://localhost:8090/login](http://localhost:8090/login) - Any other route should redirect to the
  login page.
    - **Credentials**:
        - **Admin**:
            - Username: admin
            - Password: admin123
        - **Guest**:
            - Username: guest
            - Password: guest123

## Cleaning Build Files

To clean the build files, execute the following command:

```
mvnw clean
```
