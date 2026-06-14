# SauceDemo Test Automation

Automated UI test suite for [SauceDemo](https://www.saucedemo.com/) built with Selenium WebDriver and Cucumber BDD.

![CI](https://github.com/KristinaMCode/ECommerce/actions/workflows/tests.yml/badge.svg)                                                                                                                                          
[![Allure Report](https://img.shields.io/badge/Allure-Report-brightgreen)](https://kristinamcode.github.io/ECommerce)

## Tech Stack

- **Java 17**
- **Selenium WebDriver 4.18** — browser automation
- **Cucumber 7.15** — BDD framework
- **JUnit 4** — test runner
- **PicoContainer** — dependency injection for shared WebDriver
- **WebDriverManager** — automatic ChromeDriver management
- **Maven** — build and dependency management
- **GitHub Actions** — CI/CD pipeline
- **Allure** — test reporting

## Project Structure

src/test/java/
├── config/
│   ├── TestConfig.java       # URLs and credentials constants
│   └── TestContext.java      # Shared WebDriver instance
├── pages/                                                                                                                                                                                                                       
│   ├── BasePage.java         # Explicit waits and shared utilities
│   ├── LoginPage.java                                                                                                                                                                                                           
│   ├── InventoryPage.java
│   └── CartPage.java                                                                                                                                                                                                            
├── steps/
│   ├── Hooks.java            # Before/After scenario setup                                                                                                                                                                      
│   ├── LoginSteps.java
│   ├── InventorySteps.java
│   └── CartSteps.java                                                                                                                                                                                                           
└── runners/
└── TestRunner.java

src/test/resources/
├── features/
│   ├── open_saucedemo.feature
│   └── shopping_cart.feature
└── data/                                                                                                                                                                                                                        
└── invalid_users.csv

## Test Scenarios

### Login
- Verify page URL and title
- Successful login with valid credentials
- Login validation with multiple users from CSV file

### Shopping Cart
- Add product to cart and verify
- Add product to cart, remove it and verify cart is empty

### Inventory
- Sort products by name Z to A and verify first product

## Test Strategy

- `@smoke` — critical path tests, run on every push and pull request
- `@regression` — full suite, runs automatically every night at 2am UTC

## Live Test Report

Allure report published after every run:                                                                                                                                                                                         
**[View Live Report](https://kristinamcode.github.io/ECommerce)**

## How to Run Locally

**Prerequisites:** Java 17, Maven, Google Chrome

  ```bash
  # Clone the repository
  git clone https://github.com/KristinaMCode/ECommerce.git
  cd ECommerce                                                                                                                                                                                                                     
   
  # Run all tests                                                                                                                                                                                                                  
  mvn test        

  # Run only smoke tests
  mvn test -Dcucumber.filter.tags="@smoke"
                                                                                                                                                                                                                                   
  # Run only regression tests
  mvn test -Dcucumber.filter.tags="@regression"                                                                                                                                                                                    
                  
  CI/CD

  Tests run automatically on every push and pull request via GitHub Actions on a clean Ubuntu environment with headless Chrome. Allure report is published to GitHub Pages after every run.