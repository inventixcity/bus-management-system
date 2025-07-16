#!/bin/bash

# Bus Management System - Run Script
# Author: Umer Fraz Ahmed

echo "🚌 Starting Bus Management System..."
echo "=================================="

# Check if compiled classes exist
if [ ! -d "target/classes" ]; then
    echo "📦 Compiling project..."
    mvn clean compile
    if [ $? -ne 0 ]; then
        echo "❌ Compilation failed!"
        exit 1
    fi
fi

# Run the application
echo "🚀 Launching application..."
mvn javafx:run

# Alternative method if mvn javafx:run doesn't work
# java --module-path /path/to/javafx/lib --add-modules javafx.controls,javafx.fxml -cp target/classes com.example.busmanagementsystem.HelloApplication
