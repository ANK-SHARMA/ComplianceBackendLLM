# ComplianceLLMBackend

ComplianceLLM is a project that provides a compliance checking service using Spring Boot and external APIs like OpenAI. The service allows you to input a webpage and verify its content against a compliance policy. It leverages several design patterns such as Strategy, Decorator, Factory, and Command for a flexible and scalable architecture.
## Getting Started

To set up and run the ComplianceLLM server, follow these steps:

### 1. Clone the Repository

```console
git clone https://github.com/nishant-singh13/complianceLLM.git
cd complianceLLM 
```
## 2. Add Environment Variables
```console
OPEN_API_KEY= "your_api_key"
COMPLIANCE_POLICY_LINK=https://stripe.com/docs/treasury/marketing-treasury
```

## 3. Build And Run
```console
./gradlew bootRun
```

## 4. Access the Application
Once the containers are up, you can access the application at http://localhost:8000.\
Here is swagger link : http://localhost:8080/swagger-ui.html

## 5. Project Structure
```console
src/main/java/com/compliance/compliancetestllm/
    ├── client/                           // OpenAI client logic
    │   ├── OpenAIClient.java             // Interface for OpenAI client
    │   ├── BasicOpenAIClient.java        // Implementation of OpenAI client
    │   ├── decorator/
    │       └── LoggingOpenAIClientDecorator.java  // Adds logging to OpenAI requests
    ├── service/                          // Core services
    │   ├── ComplianceService.java        // Main service for compliance checks
    │   ├── WebService.java               // Handles webpage content fetching and extraction
    ├── strategy/                         // Strategy pattern for compliance checks
    │   ├── ComplianceCheckStrategy.java  // Interface for compliance strategies
    │   ├── ApiComplianceCheckStrategy.java  // Strategy for API-based compliance checks
    │   ├── DatabaseComplianceCheckStrategy.java  // Placeholder for database compliance checks
    ├── factory/                          // Factory for compliance strategies
    │   └── ComplianceCheckFactory.java   // Creates instances of compliance strategies
    ├── command/                          // Command pattern for compliance actions
    │   ├── Command.java                  // Command interface
    │   └── ComplianceCheckCommand.java   // Encapsulates compliance check actions
    ├── controller/                       // REST controllers
    │   └── ComplianceController.java     // Main controller for compliance endpoints
    └── dto/                              // Data Transfer Objects (DTOs)
        ├── ChatRequest.java              // Request object for OpenAI API
        └── ChatResponse.java             // Response object from OpenAI API
```

## 6. Design Patterns
The project uses several design patterns to ensure flexibility and scalability:

Strategy Pattern:
Used for handling different types of compliance checks (API-based, database-based).

Example classes:

ApiComplianceCheckStrategy
DatabaseComplianceCheckStrategy
Decorator Pattern:
Adds logging functionality to the OpenAI client.

Example class:

LoggingOpenAIClientDecorator
Factory Pattern:
Centralized creation of compliance check strategies based on the input type.

Example class:

ComplianceCheckFactory
Command Pattern:
Encapsulates compliance check actions, making it easier to manage and execute them.

Example class:

ComplianceCheckCommand

## 7. API Usage
You can interact with the compliance checking API as follows:

## 1. Health Check
Endpoint: /
Method: GET
Response: String confirming the service is up.

## 2. Compliance Check
Endpoint: /compliance/check?url=https://mercury.com/
Method: GET
Response: A compliance check result as a string.

```console
Endpoint: /compliance/check?url=https://mercury.com/
Method: GET
Response:
String

Sample Res: 
The webpage content has been reviewed against the compliance policy. It appears to be in line with the compliance standards mentioned in the policy. The content provides clear information about the product and services offered by Mercury, including details about banking, treasury, corporate credit cards, invoicing, and accounting automations. The content also mentions important compliance details such as FDIC insurance, regulated partners, and fraud monitoring. Additionally, the pricing information is clearly stated, and there is transparency about the company being a financial technology company and not an FDIC-insured bank. Overall, the content seems to adhere to the compliance policy.
```
