# QuoteGraph - GraphQL Quote Service

A Spring Boot GraphQL API service for retrieving real-time stock quote data using Netflix DGS framework.

## Overview

QuoteGraph is a microservice that provides a GraphQL interface for querying stock market quotes. It leverages the Netflix DGS (Domain Graph Service) framework to deliver a modern, type-safe GraphQL API.

## Features

- 🚀 GraphQL API for stock quotes
- 📊 Query single or multiple stock symbols
- 🔧 Built with Netflix DGS framework
- 🌐 Interactive GraphiQL playground
- ☕ Java 17 + Spring Boot 3.2.0

## Tech Stack

- **Java 17**
- **Spring Boot 3.2.0**
- **Netflix DGS 8.1.1** - GraphQL framework
- **Lombok 1.18.36** - Reduce boilerplate code
- **Maven** - Build tool

## Prerequisites

- Java 17 (JDK)
- Maven 3.6+

## Getting Started

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd QuoteConnector
```

2. Build the project:
```bash
mvn clean install
```

### Running the Application

#### Option 1: Using Maven (Recommended for Development)
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
mvn spring-boot:run
```

#### Option 2: Using JAR
```bash
java -jar target/quote-graph-1.0.0.jar
```

The application will start on **port 8083**.

## API Usage

### GraphQL Endpoint
`POST http://localhost:8083/graphql`

### GraphiQL Playground
Open in browser: `http://localhost:8083/graphiql`

### Example Queries

#### Query a Single Quote
```graphql
{
  quote(symbol: "AAPL") {
    id
    symbol
    price
    change
    changePercent
    volume
    timestamp
  }
}
```

#### Query Multiple Quotes
```graphql
{
  quotes(symbols: ["AAPL", "GOOGL", "MSFT"]) {
    symbol
    price
    change
    changePercent
  }
}
```

### Using cURL
```bash
curl -X POST http://localhost:8083/graphql \
  -H "Content-Type: application/json" \
  -d '{"query":"{ quote(symbol: \"AAPL\") { symbol price change changePercent } }"}'
```

## Project Structure

```
QuoteConnector/
├── src/
│   ├── main/
│   │   ├── java/com/cnbc/quotegraph/
│   │   │   ├── QuoteGraphApplication.java    # Main application
│   │   │   ├── model/
│   │   │   │   └── Quote.java                # Quote data model
│   │   │   └── resolver/
│   │   │       └── QuoteResolver.java        # GraphQL resolvers
│   │   └── resources/
│   │       ├── application.yml                # Application config
│   │       └── graphql/
│   │           └── schema.graphqls            # GraphQL schema
│   └── test/                                  # Unit tests
├── pom.xml                                    # Maven configuration
└── README.md
```

## Configuration

Application configuration is in `src/main/resources/application.yml`:

```yaml
server:
  port: 8083

spring:
  application:
    name: quote-graph

dgs:
  graphql:
    schema-locations: classpath:graphql/**
```

## GraphQL Schema

The API exposes the following schema:

```graphql
type Query {
    quote(symbol: String!): Quote
    quotes(symbols: [String!]!): [Quote!]!
}

type Quote {
    id: ID!
    symbol: String!
    price: Float!
    change: Float!
    changePercent: Float!
    volume: Int!
    timestamp: String!
}
```

## Development

### Building
```bash
mvn clean package
```

### Running Tests
```bash
mvn test
```

### Code Style
The project uses Lombok to reduce boilerplate code. Make sure your IDE has Lombok plugin installed.

## Troubleshooting

### Java Version Issues
If you encounter Java version errors, ensure Java 17 is active:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
java -version
```

### Port Already in Use
If port 8083 is already in use, change it in `application.yml`:
```yaml
server:
  port: 8084
```

## License

[Add your license here]

## Contact

[Add contact information]
