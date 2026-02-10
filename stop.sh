#!/bin/bash

# QuoteGraph Stop Script
echo "🛑 Stopping QuoteGraph..."

# Check if running
if ! lsof -ti:8083 > /dev/null 2>&1; then
    echo "ℹ️  No application running on port 8083"
    exit 0
fi

# Kill the process
lsof -ti:8083 | xargs kill -9

# Verify it stopped
sleep 1
if ! lsof -ti:8083 > /dev/null 2>&1; then
    echo "✓ Application stopped successfully"
else
    echo "⚠️  Failed to stop application"
    exit 1
fi
