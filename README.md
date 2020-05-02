# oslobysykkeltest
Simple spring app that query OsloBsykkel API and presents for each station number of available bikes and docks.  


**To run the application:** 

You can run the application from the command line with Gradle or Maven.
If you use Maven, you can run the application by using ./mvnw spring-boot:run. Alternatively, you can build the JAR file with ./mvnw clean package and then run the JAR file, as follows:

`java -jar target/oslobysykkeltest-0.1.0.jar`

The list of stations are displayed at `http://localhost:8080/`


**Notes:** 
* The app uses rest templates for api calls, and thymleaf for presentation
* Simple logging features are added
* The app queries oslo bysykkel API every 15 second
* Errors are loged to console 
