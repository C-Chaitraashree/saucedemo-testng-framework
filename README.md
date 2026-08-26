# SauceDemo Automation Framework

UI, API, database, and CI/CD testing project built on saucedemo.com and reqres.in.

## Tech Stack
Java, Selenium WebDriver, TestNG, Postman, MySQL, Jenkins, Maven, ExtentReports

## Project Structure
```
src/main/java/utils        -> DriverFactory, DBUtil
src/test/java/pages        -> Page Object classes (LoginPage, HomePage, CartPage, CheckoutPage)
src/test/java/tests        -> TestNG test classes
src/test/java/listeners    -> ExtentReports TestNG listener
sql/                        -> schema.sql and queries.sql
postman/                    -> Postman collection and environment
Jenkinsfile                 -> CI/CD pipeline
testng.xml                  -> Test suite configuration
```

## What This Covers
- UI automation on saucedemo.com using Page Object Model
- Login tested with multiple credential sets via TestNG DataProvider
- Add to cart, remove from cart, full checkout flow
- Product sort validated for all 4 sort options (name A-Z/Z-A, price low-high/high-low) via TestNG DataProvider
- TestNG grouping (smoke/regression) and parallel test execution
- Extent HTML report generated after every run
- API testing on reqres.in via Postman collection (GET/POST/PUT/DELETE)
- Postman pre-request script generates random test data
- Postman test scripts validate status codes and response body
- Local MySQL database with products, customers, orders, order_items tables
- 14 SQL queries covering JOIN, GROUP BY/HAVING, subqueries, aggregates, ORDER BY/LIMIT
- DB validation test: compares the product price shown on the UI against the price stored in the database
- Jenkins pipeline runs the suite on every push (via GitHub webhook) and on a nightly schedule, and emails the team on failure

## How to Run

### Setup Database
```
mysql -u root -p < sql/schema.sql
```

### Run UI + DB Tests
```
mvn clean test
```

### Run Only Smoke Group
```
mvn test -Dgroups=smoke
```

### View Report
Open `test-output/ExtentReport.html` in a browser after the run.

### Jenkins Push Trigger
`githubPush()` in the Jenkinsfile needs the GitHub plugin and a webhook configured on the GitHub repo pointing to the Jenkins server. The Jenkinsfile uses `bat` steps for a Windows Jenkins agent; Maven must be on the system PATH for the Jenkins service to find it.

### Run API Tests
Import `postman/reqres-collection.json` and `postman/reqres-environment.json` into Postman, select the environment, and run the collection.

## Sample Report
ExtentReport.html is generated fresh in `test-output/` after each `mvn test` run and shows pass/fail status per test method.
