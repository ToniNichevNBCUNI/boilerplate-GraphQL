# QuoteGraph - GraphQL Quote Service (Apollo Federation Subgraph)

A Spring Boot GraphQL API service for retrieving real-time stock quote data using Netflix DGS framework with Apollo Federation v2 support.

## Overview

QuoteGraph is a **federated GraphQL subgraph** that provides quote data for stock symbols. It's designed to work with Apollo Router as part of a supergraph architecture, allowing other subgraphs to reference and extend quote information.

## Features

- 🚀 **Apollo Federation v2** subgraph
- 📊 Query single or multiple stock symbols
- 🔑 Entity resolution by symbol (federation key)
- 🔧 Built with Netflix DGS framework
- 🌐 Interactive GraphiQL playground
- ☕ Java 17 + Spring Boot 3.2.0
- 🔗 Ready for Apollo Router integration

## Tech Stack

- **Java 17**
- **Apollo Federation 4.3.0** - Federation support
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

#### Option 1: Using Scripts (Easiest)
```bash
./start.sh    # Start the application
./stop.sh     # Stop the application
```

#### Option 2: Using Maven
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
mvn spring-boot:run
```

#### Option 3: Using JAR
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
**federated schema**:

```graphql
extend schema
  @link(url: "https://specs.apollo.dev/federation/v2.3", import: ["@key", "@shareable"])

type Quote @key(fields: "symbol") {
    id: ID!
    symbol: String!
    price: Float!
    change: Float!
    changePercent: Float!
    volume: Int!
    timestamp: String!
}

type Query {
    quote(symbol: String!): Quote @shareable
    quotes(symbols: [String!]!): [Quote!]! @shareable
}
```

### Federation Features

- **Entity**: `Quote` is a federated entity with `symbol` as the key
- **@key(fields: "symbol")**: Allows other subgraphs to reference quotes by symbol
- **@shareable**: Queries can be resolved by multiple subgraphs
- **Entity Resolution**: Other subgraphs can extend Quote or reference it

### Example: Using Quote in Another Subgraph

```graphql
# In another subgraph (e.g., portfolio-service)
type Portfolio @key(fields: "id") {
  id: ID!
  holdings: [Holding!]!
}

type Holding {
  symbol: String!
  shares: Int!
  quote: Quote  # References Quote from quote-graph subgraph
}

extend type Quote @key(fields: "symbol") {
  symbol: String! @external
  # This subgraph can add additional fields to Quote
}
```

## Apollo Federation Setup

### Subgraph Configuration

This service exposes its schema at:
- **Subgraph URL**: `http://localhost:8083/graphql`
- **SDL Endpoint**: The schema is automatically available via introspection

### Router Configuration

To include this subgraph in your Apollo Router, add it to your `supergraph.yaml`:

```yaml
federation_version: =2.3.0
subgraphs:
  quote-graph:
    routing_url: http://localhost:8083/graphql
    schema:
      subgraph_url: http://localhost:8083/graphql
```

### Compose Supergraph

Using Rover CLI:
```bash
rover supergraph compose --config supergraph.yaml > supergraph-schema.graphql
```

### Start Apollo Router

```bash
./router --supergraph supergraph-schema.graphql   changePercent: Float!
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
