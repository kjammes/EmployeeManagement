# EmployeeManagement
Spring Boot REST API with endpoints performing CRUD operations.

### Pre-requisites for running application

1. Java Version 17+
2. IntelliJ (Recommended IDE for opening and running project)
3. Postman for testing API routes
4. MySQL Database installed.

> *EmployeeManagement.postman_collection.json* file can be directly imported into postman on your device and you'll find all the sample requests with proper headers and body(if required).

### You'll have to make following changes in application.yml

1. Change database name OR Create a database named mydb in MySQL on your system
2. Change username & password according to your settings
   (Other properties can be kept as it is)

This application provides different routes for performing different actions

1. http://localhost:8080/api/employee/all/ GET Request. Returns list of all the employees in our database(DB)
2. http://localhost:8080/api/employee/create/ POST Request. Send a post request with firstName, lastName and email in JSON format to create a new user(ID is automatically assigned).
3. http://localhost:8080/api/employee/{id}/ GET Request. It'll return the employee object with specified ID. Will return 404 in case employee not found.
4. http://localhost:8080/api/employee/{id}/ PUT Request. It'll update the body with the new values in passed JSON object. The parameters are same as creating a new employee.
5. http://localhost:8080/api/employee/1/ DELETE Request. It'll delete the employee record with specified ID. Will return 404 in case invalid ID is passed.

All of the above requests require CLIENT_ID and CLIENT_SECRET to be set in headers of request for authorization. The values will be constant for now and they're defined in application.yml file. 