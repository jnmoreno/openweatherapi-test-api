# openweatherapi-test-api


## Required software
- Java JDK 11+
- Maven installed and in your classpath

## How to execute the tests
You can open each test class on src\\test\\java and execute all of them, but I recommend you run it by the command line. It enables us to run in different test execution strategies.

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
**test**
Base Test that sets the initial aspects to make the requests using RestAssured. It also has the configuration to deal with BigDecimal returns and SSL configuration.

**provider**
JUnit 5 Arguments to reduce the amount of code and maintenance for the functional tests

**suite**
It contains a class having the data related to the test groups.

### src/test/java

## Libraries
- [RestAssured] library to test REST APIs
- JUnit 5 to support the test creation
- java-faker to generate fake data
- Allure Report as the testing report strategy

All the parameters, except the -Dgroups are pointing to Heroku because we can't run it locally. It's a great example about how can you set different attribute values to run your tests.
