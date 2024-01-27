# Automated Web Testing with Java, Selenium, and Cucumber
![Static Badge](https://img.shields.io/badge/Java-logo?style=for-the-badge&logo=openjdk&logoColor=white&labelColor=rgb(229%2C%2031%2C%2036)&color=rgb(22%2C%2027%2C%2034))
![Static Badge](https://img.shields.io/badge/Selenium-logo?style=for-the-badge&logo=selenium&logoColor=white&labelColor=rgb(0%2C%20174%2C%200)&color=rgb(22%2C%2027%2C%2034))
![Static Badge](https://img.shields.io/badge/Cucumber-logo?style=for-the-badge&logo=cucumber&logoColor=black&labelColor=rgb(35%2C%20217%2C%20108)&color=rgb(22%2C%2027%2C%2034))

This project offers a framework and tools for automated web testing using Java, Selenium, and Cucumber, following Behavior-Driven Development (BDD) best practices and employing the Page Object Model design pattern.

## Testing demoblaze.com Features 🧪

This suite of tests is specifically designed to validate and test features on the [demoblaze.com](https://www.demoblaze.com) website. You'll find feature files under the `tests/features` directory related to signup, login and adding products to the cart.

![JavaSeleniumCucumber](https://github.com/carlosvagnoni/JavaSeleniumCucumber/assets/106275103/73d6bb26-c86a-4ddc-8e1b-a9c376de3796)

## Table of Contents 📑
- [Requirements](#requirements)
- [Folder Structure](#folder-structure)
- [Installation](#installation)
- [Configuration](#configuration)
- [Test Execution](#test-execution)
- [Contact](#contact)

## <a id="requirements">Requirements 📋</a>

- JDK 21
- Lombok 1.18.30
- Selenium 4.15.0
- Cucumber 7.14.0

## <a id="folder-structure">Folder Structure 📂</a>

- **config.json:** Configuration file for variable data.
- **pom.xml:** Maven configuration file specifying project dependencies.
- **run.bat:** Script file for Windows environment execution.

### Directory "src/test/java/com/automatedtests/demoblaze"

- **TestRunner.java:** Cucumber test runner class.
  
#### Directory "features"

- **001_Signup.feature:** Specification file for signup feature.
- **002_Login.feature:** Specification file for login feature.
- **003_AddProductToCart.feature:** Specification file for adding products to cart feature.

#### Directory "pages"

- **BasePage.java:** Page class for header and footer.
- **CartPage.java:** Page class for cart functionality.
- **HomePage.java:** Page class for home page functionality.
- **ProductPage.java:** Page class for product-related functionality.

#### Directory "steps"

- **AddProductToCartSteps.java:** Step definitions for adding products to cart.
- **Hooks.java:** Cucumber hooks for setup and teardown.
- **LoginSteps.java:** Step definitions for login functionality.
- **SignupSteps.java:** Step definitions for signup functionality.

#### Directory "utils"

- **Configuration.java:** Utility class for configuration settings.
- **Drivers.java:** Configuration and management of WebDriver.
- **Expect.java:** Custom assertion functions.
- **PageObject.java:** Definition of the base structure of the Page Object Model.

### Directory "resources"

- **log4j.properties:** Logging configuration file using Log4j.

## <a id="installation">Installation 🛠️</a>

1. Clone this repository:

    ```bash
    git clone https://github.com/carlosvagnoni/JavaSeleniumCucumber.git
    cd JavaSeleniumCucumber
    ```

2. Compile the project:

    ```bash
    mvn clean compile
    ```

## <a id="configuration">Configuration ⚙️</a>

- Make sure you have a browser installed and configured in the script (Chrome, Edge, or Firefox).
- You can configure the config.json file to adjust parameters such as the base URL(url) or the desired browser to use(navigator).

## <a id="test-execution">Test Execution ▶️</a>

Run all the tests:

```bash
mvn test
```

Open report:

```bash
start "" "target\reports\demoblaze.html"
```

**NOTE:**

- Set up the respective environment variables beforehand.
- On Windows environments, you can directly execute the `run.bat` file.

## <a id="contact">Contact 📧</a> 

If you have any questions or suggestions, feel free to contact me through my social media accounts.

Thank you for your interest in this project!
