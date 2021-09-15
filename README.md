## _OpenWeather API Test_
**This project delivers the automation of some API tests, using the website's APIS https://openweathermap.org/**

## Languages and Frameworks
This project using the following languages and frameworks:

* [Java 11](https://openjdk.java.net/projects/jdk/11/) as the programming language
* [JUnit5](https://junit.org/junit5/) as the UnitTest framework to support the test creation
* [REST-assured](https://www.selenium.dev/) as framework to test REST API
* [Allure Report](https://docs.qameta.io/allure/) as the testing report strategy



## How to execute the tests
You can open each test class on `src\test\java` and execute all of them, but I recommend you run it by the command line. It enables us to run in different test execution strategies.

## Running the test suites
The test suites can be run directly by your IDE or by command line. If you run mvn test all the tests will execute because it's the regular Maven lifecycle to run all the tests.
To run different suites based on the groups defined for each test you must inform the property -Dgroups and the group names. The example below shows how to run the test for each test suites:

| Test Suites | Command |
| ------ | ------ |
| Current tests | mvn test -D groups="current" |
| Forecast tests | mvn test -D groups="forecast" |
| Regression tests | mvn test -D groups="regression" |

## Generating the test report
This project uses Allure Report to automatically generate the test report. There are some configuration to make it happen:

- aspectj configuration on `pom.xml` file
- allure.properties file on `src/test/resources`

You can use the command line to generate it in two ways:
- `mvn allure:serve:` will open the HTML report into the browser
- `mvn allure:report:` will generate the HTML port at `target/site/allure-maven-plugin` folder

## About the Project Structure
### src/main/java
**Provider**
JUnit 5 Arguments to reduce the amount of code and maintenance for the functional tests

**Suite**
It contains a class having the data related to the test groups.

* ***Configuration file:*** there are 2 properties files on `src/test/java/resources/`: <br>
  -- `allure.properties`: path to allure reports<br>
  -- `datas.properties`: contains contains APIKEY, ID CITY, CITY NAME, ZIP CODE, LATITUDE, LONGITUDE, UNITS and IDIOM


## Set up the environment
* Install Java 11
* Install Maven and configure in your classpath
* Install Allure
* Install git

### Clone Project
Clone the project from github link. Navigate to your workspace folder, at the terminal, type the command: `https://github.com/jnmoreno/openweatherapi-test-api.git`.
