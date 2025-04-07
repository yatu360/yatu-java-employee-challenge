# Employee API

A RESTful API for managing employees, implemented using Spring Boot. It integrates with an external system and provides endpoints to create, read, update, and delete employee data, along with some analytics features.

---

## 🚀 Base URL

```
http://localhost:8111/employee
```

---

## 📦 API Endpoints

### 🔍 Get All Employees

- **GET** `/getAllEmployees`
- **Description:** Returns a list of all employees.

### 🔍 Search Employees by Name

- **GET** `/search?name={searchTerm}`
- **Description:** Search for employees by partial or full name match.

### 🔍 Get Employee by ID

- **GET** `/{id}`
- **Description:** Retrieve a specific employee by their ID.

### 💵 Get Highest Salary

- **GET** `/highest-salary`
- **Description:** Returns the highest salary among all employees.

### 🏆 Top 10 Highest Earners

- **GET** `/top-ten-earners`
- **Description:** Returns a list of the top 10 employee names by salary.

### ➕ Create Employee

- **POST** `/`
- **Description:** Creates a new employee.
- **Body Example:**

```json
{
  "employee_name": "Tiger Nixon",
  "employee_salary": 320800,
  "employee_age": 61,
  "employee_title": "Vice Chair Executive Principal",
  "employee_email": "tnixon@company.com"
}
```

### ✏️ Update Employee by ID

- **PUT** `/{id}`
- **Description:** Updates an existing employee by ID.
- **Body Example:** Same as POST.

### ❌ Delete Employee by ID

- **DELETE** `/{id}`
- **Description:** Deletes an employee by ID.

---

## 🔧 Tech Stack

- Java 17
- Spring Boot 3
- Gradle
- Lombok
- Spring AOP (method logging)
- JUnit 5 & MockMVC (for testing)

---

## 🧪 Running Tests

```bash
./gradlew test
```

---

## 📜 Logging with AOP

This project includes Spring AOP to automatically log method calls in `controller` and `service` layers:

```
➡️ Entering: com.reliaquest.api.service.EmployeeServiceImpl.getAllEmployees() with arguments = []
⬅️ Exiting: com.reliaquest.api.service.EmployeeServiceImpl.getAllEmployees() with result = [...]
```

---

## 📁 Structure

```
com.reliaquest.api
├── controller        # REST endpoints
├── service           # Business logic
├── model             # Employee model
├── aop               # LoggingAspect.java
└── ApiApplication    # Main app
```

---

## ✅ Improvements for future
Add contract tests for external API
Use @Valid and JSR-380 annotations for request validation
Implement global exception handling (@ControllerAdvice)
Standardize error responses
Add response time logging with AOP
Add Micrometer metrics + Prometheus/Grafana support
Add authentication (e.g. JWT or Basic Auth)
Add role-based authorization
Add CI/CD pipeline (GitHub Actions or GitLab CI)
Refactor hardcoded URLs into config properties

# Implement this API

#### In this assessment you will be tasked with filling out the functionality of different methods that will be listed further down.

These methods will require some level of API interactions with Mock Employee API at http://localhost:8112/api/v1/employee.

Please keep the following in mind when doing this assessment:
* clean coding practices
* test driven development
* logging
* scalability

### Endpoints to implement

_See `com.reliaquest.api.controller.IEmployeeController` for details._

getAllEmployees()

    output - list of employees
    description - this should return all employees

getEmployeesByNameSearch(...)

    path input - name fragment
    output - list of employees
    description - this should return all employees whose name contains or matches the string input provided

getEmployeeById(...)

    path input - employee ID
    output - employee
    description - this should return a single employee

getHighestSalaryOfEmployees()

    output - integer of the highest salary
    description - this should return a single integer indicating the highest salary of amongst all employees

getTop10HighestEarningEmployeeNames()

    output - list of employees
    description - this should return a list of the top 10 employees based off of their salaries

createEmployee(...)

    body input - attributes necessary to create an employee
    output - employee
    description - this should return a single employee, if created, otherwise error

deleteEmployeeById(...)

    path input - employee ID
    output - name of the employee
    description - this should delete the employee with specified id given, otherwise error

### Testing
Please include proper integration and/or unit tests.
