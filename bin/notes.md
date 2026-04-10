# Best Practices

1. clean coding principles
2. branching model
3. design patterns

## reporting tools

1. extent
2. allure
3. testNG

## logging

1. log4j

## dependency management

1. maven

## Components of Automation Framework

1. Reporting
    - Allure, Extent
2. Logging
    - Log4J
3. UI
    - Selenium WebDriver
4. Test Library
    - TestNG

### TestNG

1. executing testing from terminal or with testng.xml
2. in-built reporting capabilities
3. retry logic (IRetry Analyzer)
4. parallel execution
5. data-driven testing (DataProvider)
6. grouping and prioritization of tests
7. integration with build tools (Maven, Gradle)
8. annotations for test lifecycle management
9. parameterization of tests
10. support for test configuration and setup/teardown methods

### Data-driven testing

1. JSON
    - jackson
    - gson
2. CSV
    - opencsv
3. Excel
    - Apache POI
4. Fake Test Data creation
    - Faker

### Code Quality

- sonar check
  - java coding standards
    - code duplicacy
    - length of class / method
    - code smells

### environment config

- ENV
  - dev.properties
    - int.properties
    - stg.properties

### utility component

- Reusability
- webdriver methods are wrapped in a custom class (ex. browserUtility) [abstraction]

### design patterns

- ways of creating classes that ensures you're implementing OOPS in correct way
- POM (Page Object Model)
- Singleton
- Factory
- Builder
- Strategy
- Observer
- Decorator
- Adapter
- Facade
- Command
- MVC (Model-View-Controller)
- MVVM (Model-View-ViewModel)
- Dependency Injection
- Repository
- Service Locator
- Template Method
- Iterator
- Composite
- State
- Proxy

### Feature

- feature using which I can execute my automation framework from terminal

### CI/CD

- Jenkins
- GitHub Actions
- GitLab CI/CD
- CircleCI
- Travis CI
- Bamboo
- Azure DevOps
- TeamCity
- AWS CodePipeline
- Bitbucket Pipelines
- Docker
- Kubernetes

### cross browser testing

- Selenium Grid
- BrowserStack
- Sauce Labs
- CrossBrowserTesting
- LambdaTest
- TestingBot
- Perfecto
- Applitools
- Ghost Inspector
- Kobiton


# Notes for learning during framework development

``` WebDriver driver = new ChromeDriver(); ```

- ChromeDriver is a class that implements the WebDriver interface. It is used to create an instance of the Chrome browser and interact with it using Selenium WebDriver.

- anything that exists in reality, anything that occupies space and can be touched, seen, felt, heard, or smelled is an object. In programming, an object is an instance of a class that encapsulates data and behavior. It is a fundamental concept in object-oriented programming (OOP) that allows us to model real-world entities and their interactions in code. An object can have properties (attributes) and methods (functions) that define its characteristics and actions.

- loose coupling: when two classes are independent of each other, they are loosely coupled. This means that changes in one class will not affect the other class. Loose coupling is desirable in software design because it promotes flexibility and maintainability.

- tight coupling: when two classes are dependent on each other, they are tightly coupled. This means that changes in one class will affect the other class. Tight coupling is undesirable in software design because it reduces flexibility and maintainability.


1. What happens when an object is created?
    - class is loaded into memory
    - constructor is called
    - heap memory is allocated for the object
    - object is initialized with default values
    - reference to the object is returned

2. which class is responsible for handling locators?
    - By class is responsible for handling locators in Selenium WebDriver. It provides various methods to locate elements on a web page, such as By.id(), By.name(), By.className(), By.tagName(), By.linkText(), By.partialLinkText(), By.cssSelector(), and By.xpath(). These methods return a By object that can be used to find elements on the page using the WebDriver's findElement() or findElements() methods.
    - By is an abstract class. we can't create an object of By class. we can only use its static methods to create By objects.

    - static methods can be accessed by classname.
    - all the methods of By class are static methods. we can call them using By.id(), By.name(), etc.

