# ComplianceLLMBackend

ComplianceLLM is a project that provides a compliance checking service using Docker and Docker Compose. This service allows you to input a webpage and verify its content against a compliance policy.

## Getting Started

To set up and run the ComplianceLLM server, follow these steps:

### 1. Clone the Repository

```console
git clone https://github.com/nishant-singh13/complianceLLM.git
cd complianceLLM 
```
## 2. Add Environment Variables
```console
OPEN_API_KEY= your api key
COMPLIANCE_POLICY_LINK=https://stripe.com/docs/treasury/marketing-treasury
```

## 2. Build And Run
```console
./gradlew bootRun
```

## 4. Access the Application
Once the containers are up, you can access the application at http://localhost:8000.\
Here is swagger link : http://localhost:8080/swagger-ui.html

## 5. API Usage
You can interact with the compliance checking API as follows:
```console

Endpoint: /compliance/check?url=https://mercury.com/
Method: GET
Response:
String

Sample Res: 
The webpage content has been reviewed against the compliance policy. It appears to be in line with the compliance standards mentioned in the policy. The content provides clear information about the product and services offered by Mercury, including details about banking, treasury, corporate credit cards, invoicing, and accounting automations. The content also mentions important compliance details such as FDIC insurance, regulated partners, and fraud monitoring. Additionally, the pricing information is clearly stated, and there is transparency about the company being a financial technology company and not an FDIC-insured bank. Overall, the content seems to adhere to the compliance policy.
```
