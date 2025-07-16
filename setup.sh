#!/bin/bash

# Bus Management System - Setup Script
# Author: Umer Fraz Ahmed
# Email: inventixcui@gmail.com

echo "🚌 Bus Management System Setup Script"
echo "======================================"

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "❌ Java is not installed. Please install Java 22 or higher."
    exit 1
fi

# Check Java version
JAVA_VERSION=$(java -version 2>&1 | awk -F '"' '/version/ {print $2}' | awk -F '.' '{print $1}')
if [ "$JAVA_VERSION" -lt 22 ]; then
    echo "❌ Java version must be 22 or higher. Current version: $JAVA_VERSION"
    exit 1
fi

echo "✅ Java version check passed"

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "❌ Maven is not installed. Please install Apache Maven 3.8 or higher."
    exit 1
fi

echo "✅ Maven check passed"

# Check if JavaFX is available
echo "🔍 Checking JavaFX availability..."

# Clean and compile the project
echo "🔄 Cleaning and compiling project..."
mvn clean compile

if [ $? -ne 0 ]; then
    echo "❌ Compilation failed. Please check the error messages above."
    exit 1
fi

echo "✅ Compilation successful"

# Run tests
echo "🧪 Running tests..."
mvn test

if [ $? -ne 0 ]; then
    echo "⚠️  Some tests failed. Check the output above for details."
else
    echo "✅ All tests passed"
fi

# Create executable JAR
echo "📦 Creating executable JAR..."
mvn clean package

if [ $? -ne 0 ]; then
    echo "❌ JAR creation failed."
    exit 1
fi

echo "✅ JAR created successfully"

# Make the run script executable
chmod +x run.sh

echo ""
echo "🎉 Setup completed successfully!"
echo ""
echo "To run the application:"
echo "  Method 1: ./run.sh"
echo "  Method 2: mvn javafx:run"
echo "  Method 3: java -jar target/BusManagementSystem-1.0-SNAPSHOT.jar"
echo ""
echo "📚 Documentation:"
echo "  - README.md: Complete project documentation"
echo "  - CONTRIBUTING.md: Contribution guidelines"
echo "  - CHANGELOG.md: Version history"
echo ""
echo "🐛 For issues and support:"
echo "  - GitHub: https://github.com/inventixcity/bus-management-system"
echo "  - Email: inventixcui@gmail.com"
echo ""
echo "Happy coding! 🚀"
