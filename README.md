# API test for SWAPI API 

## Tools and Technologies used

- **Java**: Programming language used for writing test scripts.
- **RestAssured**: A library for testing RESTful web services in Java.
- **Maven**: Dependency management and build automation.
- **TestNG**:  A testing framework.

## Key Directories and Files

- **`config/`**: Contains URI and endpoints of API
- **`test/`**: Holds all test classes to validate SWAPI endpoints.
- **`resources/`**: Contains JSON schema files for contract testing.
- **`pom.xml`**: Maven configuration file that manages project dependencies.
- **`testng.xml`**: Configures TestNG test execution.

## Setup Instructions

### Prerequisites

Ensure you have the following installed on your system:

- Java
- Maven

### Installation

1. **Clone the repository:**

   ```bash
   https://github.com/rathiankit03/SWAPI_API_RestAssured.git
   cd SWAPI_API_RestAssured
   ```

2. ** Install project dependencies and build the project: **

    ```bash
    mvn clean install
    ```
3. ** Run Test **

    ```bash
    mvn test
    ```    
