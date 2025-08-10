# SwagLabs Test Automation Project

This project is End-to-end automation test framework for the SwagLabs e-commerce application using Selenium WebDriver with Java. It implements the Page Object Model (POM) design pattern and includes end-to-end testing capabilities for web applications.

## 🚀 Technologies Used

- **Java** - Primary programming language
- **Selenium WebDriver** - Web automation framework
- **TestNG/JUnit** - Testing framework for test execution and reporting
- **Maven** - Dependency management and build tool
- **Page Object Model (POM)** - Design pattern for test automation
- **Allure Reports** - Advanced test reporting framework
- **Log4j2** - Logging framework
- **JSON** - Test data management

## 📁 Project Structure

```
SwagLabsProject/
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── drivers/
│   │       │   ├── BrowserFactory.java
│   │       │   └── DriverWebManager.java
│   │       ├── listeners/
│   │       │   ├── MethodListener.java
│   │       │   ├── SuiteListener.java
│   │       │   └── TestListener.java
│   │       ├── media/
│   │       │   └── ScreenShot.java
│   │       ├── Pages/
│   │       │   ├── P01_Login.java
│   │       │   ├── P02_Landing.java
│   │       │   ├── P03_Cart.java
│   │       │   └── P04_Checkout.java
│   │       └── utilities/
│   │           ├── actions/
│   │           │   ├── BrowserActions.java
│   │           │   ├── ElementActions.java
│   │           │   ├── Scrolling.java
│   │           │   └── Waits.java
│   │           ├── constants/
│   │           │   └── Constants.java
│   │           ├── dataReader/
│   │           │   ├── FilesUtils.java
│   │           │   └── JsonUtils.java
│   │           ├── logs/
│   │           │   └── LogsUtil.java
│   │           └── report/
│   │               ├── AllureUtils.java
│   │               └── CustomReport.java
│   ├── test/
│   │   └── java/
│   │       ├── TestCases/
│   │       │   ├── BaseTests.java
│   │       │   ├── EndToEnd.java
│   │       │   ├── TCs_P01_loginPage.java
│   │       │   ├── TCs_P02_landingPage.java
│   │       │   ├── TCs_P03_CartPage.java
│   │       │   └── TCs_P04_checkOut.java
│   │       └── resources/
│   │           └── test-data.json
│   └── resources/
│       └── log4j2.properties
├── target/
├── test-outputs/
│   ├── allure-results/
│   ├── logs/
│   └── screenshots/
└── pom.xml
```

## ✨ Features

- **Cross-Browser Testing**: Support for multiple browsers (Chrome, Firefox, Edge, Safari)
- **Page Object Model**: Clean separation of page elements and test logic
- **Data-Driven Testing**: JSON-based test data management
- **Comprehensive Reporting**: Allure reports with screenshots and detailed test results
- **Custom Listeners**: TestNG listeners for enhanced test monitoring
- **Screenshot Capture**: Automatic screenshot capture on test failures
- **Logging Framework**: Structured logging using Log4j2
- **Utility Classes**: Reusable components for common actions
- **End-to-End Testing**: Complete user journey testing from login to checkout
- **Modular Architecture**: Well-organized package structure for maintainability

## 🎯 Test Coverage

- **Login Functionality**: User authentication and validation
- **Product Browsing**: Landing page interactions and product selection
- **Shopping Cart**: Add/remove items, quantity management
- **Checkout Process**: User information, payment, and order completion
- **End-to-End Workflows**: Complete purchase journeys

## 🛠️ Challenges Faced

| Challenge | Cause | Solution |
|-----------|-------|----------|
| **Dynamic Element Loading** | SPA nature causing timing issues | Implemented explicit waits and custom wait utilities |
| **Cross-Browser Compatibility** | Different browser behaviors | Created BrowserFactory with browser-specific configurations |
| **Test Data Management** | Hard-coded test data in scripts | Implemented JSON-based data reader utilities |
| **Screenshot Management** | Manual screenshot capture process | Automated screenshot capture using listeners |
| **Test Reporting** | Basic TestNG reports insufficient | Integrated Allure reporting framework |
| **Element Interaction Failures** | Stale element references | Implemented element refresh mechanisms and robust locators |
| **Parallel Test Execution** | Thread safety issues | Used ThreadLocal for WebDriver instances |

## 📋 Prerequisites

- **Java Development Kit (JDK) 11+**
- **Maven 3.6+**
- **Chrome/Firefox/Edge Browser** (latest version)
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code)
- **Git** (for version control)

### Dependencies
All dependencies are managed through Maven and defined in `pom.xml`:
- Selenium WebDriver
- TestNG
- Allure TestNG
- Log4j2
- Jackson (for JSON handling)

## 🚀 Usage

### Setup
1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd SwagLabsProject
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Update test data**
   Edit `src/test/resources/test-data.json` with your test data

### Running Tests

#### Run All Tests
```bash
mvn test
```

#### Run Specific Test Suite
```bash
mvn test -Dtest=TCs_P01_loginPage
```

#### Run with Specific Browser
```bash
mvn test -Dbrowser=chrome
```

#### Run End-to-End Tests
```bash
mvn test -Dtest=EndToEnd
```

### Generating Reports

#### Allure Reports
```bash
mvn allure:serve
```

#### View Screenshots
Screenshots are automatically saved in `test-outputs/screenshots/` directory

### Configuration

#### Browser Configuration
Update `BrowserFactory.java` to modify browser settings

#### Test Data
Modify `test-data.json` for different test scenarios

#### Logging
Configure logging levels in `log4j2.properties`

## 📊 Reporting

- **Allure Reports**: Comprehensive test reports with steps, screenshots, and attachments
- **Console Logs**: Real-time test execution logs
- **Screenshots**: Automatic capture on failures
- **Custom Reports**: Additional reporting utilities available

## 🔧 Maintenance

- **Page Objects**: Update page classes when UI changes
- **Test Data**: Regularly update JSON test data files
- **Dependencies**: Keep Maven dependencies updated
- **Browser Drivers**: Ensure WebDriverManager handles driver updates

## 📝 Best Practices Implemented

- Page Object Model design pattern
- Data-driven testing approach
- Explicit waits over implicit waits
- Proper exception handling
- Comprehensive logging
- Modular and reusable components
- Clean code architecture

---

**Note**: Make sure to update browser drivers and test data according to your testing environment before running the tests.
