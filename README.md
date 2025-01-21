
# Currency Converter API Integration

## Project Overview
This is a Spring Boot application that integrates with a public API to provide real-time currency conversion functionality.

## Features
- **Fetch Exchange Rates**: The app fetches real-time currency exchange rates from a public API.
- **Currency Conversion**: Converts amounts between different currencies based on fetched rates.
- **Error Handling**: Handles API unavailability and invalid currency codes.

## Technologies Used
- Spring Boot
- Java
- Maven
- RestTemplate (for API communication)
- Postman (for testing)

## How to Run the Project Locally

1. **Clone the repository**:
   ```bash
   git clone https://github.com/Sooryamol-S/currency-converter-api.git
   ```

2. **Navigate to the project directory**:
   ```bash
   cd currency-converter
   ```

3. **Build and run the application**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. The application will be available at:
   ```bash
   http://localhost:8080
   ```

## Postman API Testing

To test the API, you can use Postman with the following endpoints:

1. **GET /api/rates?base=USD**
    - Fetches the exchange rates for the given base currency (USD by default).
    - Example URL:
      ```bash
      http://localhost:8080/api/rates?base=USD
      ```

2. **POST /api/convert**
    - Converts an amount from one currency to another.
    - Example request body:
      ```json
      {
        "from": "USD",
        "to": "EUR",
        "amount": 100
      }
      ```
    - Example URL:
      ```bash
      http://localhost:8080/api/convert
      ```

## Postman Test Example

You can use the following Postman request format to test the API:

1. **GET request** to fetch rates:
    - **URL**: `http://localhost:8080/api/rates?base=USD`
    - **Method**: GET
    - **Response**: A JSON object with the exchange rates for USD.

2. **POST request** to convert currency:
    - **URL**: `http://localhost:8080/api/convert`
    - **Method**: POST
    - **Body** (raw JSON):
      ```json
      {
        "from": "USD",
        "to": "EUR",
        "amount": 100
      }
      ```
    - **Response**: A JSON object with the conversion details.
      ```json
      {
        "from": "USD",
        "to": "EUR",
        "amount": 100,
        "convertedAmount": 94.5,
        "rate": 0.945
      }
      ```

## Testing and Error Handling
- **Postman** is used for testing the API instead of unit test classes.
- The error handling is built into the app, so if the external API is unavailable or invalid currency codes are provided, an appropriate error message will be returned.

## Project Structure
- **src/main/java**: Contains the source code for the Spring Boot application.
- **src/main/resources**: Contains application properties and configuration files.
- **pom.xml**: Maven configuration for dependencies and build.

## Notes
- If you face any issues while running the project, make sure the required port (8080) is not being used by other applications.
```
