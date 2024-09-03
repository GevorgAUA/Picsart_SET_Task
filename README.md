

**Project Name:** Picsart SET task

**Description:**

This project is a completed task which automates an e2e scenario using java, selenium webdriver and testNG. 

**Features:**

* **Parallel Execution:** Tests can be executed in parallel using TestNG's parallel execution feature.
* **Data-Driven Testing:** Tests can be data-driven using TestNG's data provider feature.
* **Page Object Model:** The project uses a page object model to separate the test logic from the page elements.
* **Reusable Methods:** The project includes reusable methods for common actions such as clicking, typing, and verifying.
* **Uses Singletone and Thread-Safe Driver:** The project uses a singletone and thread-safe driver for the web driver. It creates and uses a single instance of a thread-safe driver throughout the project.
* **Screenshots Upon Failure:** If a task fails a screenshot is done and stores in screenshots folder before closing the driver.

**Project Structure:**

* **src/main/java:** Provides with utilities for the testing project. 
* **src/test/java:** Contains the test source code for the project.
* **src/test/:** Contains the resources for the tests, including the test itself.
* **pom.xml:** Contains the Maven project configuration.

* **Requirements:**

* **Java:** Java 8 or later.
* **Selenium WebDriver:** Selenium WebDriver 3.141.59 or later.
* **TestNG:** TestNG 7.0.0 or later.

**Setup:**

1. Clone the project repository.
2. Install the required dependencies.
3. Install a driver for your browser, in TestBase class set property for the preffered browser, and put the path in the properties. 

**How to Run:**
1. Run the `testTask` located in `TestPicsart` located in the `src/test/java` folder.
