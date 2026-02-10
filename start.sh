#!/bin/bash

# QuoteGraph Start Script
echo "🚀 Starting QuoteGraph..."

# Set Java 17
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
echo "✓ Using Java 17: $JAVA_HOME"

# Check if already running
if lsof -ti:8083 > /dev/null 2>&1; then
    echo "⚠️  Application already running on port 8083"
    echo "   Run './stop.sh' first to stop it"
    exit 1
fi

# Start the application
echo "📦 Building and starting application..."
mvn clean spring-boot:run
