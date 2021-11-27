## Spring Boot REST API Demo Application
This is just a very simple application in spring-boot
The application manages employees in virtual company, and has a few endpoints to get, add, update and delete employees.    
To start the spring-boot app, execute:
```
	mvn spring-boot:run
```
By default, it starts on port 8080. You can override this in application.properties.  
Once it starts, all the endpoints are available via Swagger : http://localhost:8080/swagger-ui.html  
Please be aware that Spring does not support Java 16 at this point.  

## Author
Martin Stancl. https://www.linkedin.com/in/martin-štancl-11b591108