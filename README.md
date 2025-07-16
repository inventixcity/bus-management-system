# 🚌 Bus Management System

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![JavaFX](https://img.shields.io/badge/javafx-%23FF0000.svg?style=for-the-badge&logo=javafx&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![License](https://img.shields.io/badge/license-MIT-blue.svg?style=for-the-badge)

A comprehensive and advanced Bus Management System built with JavaFX, featuring a modern GUI, robust architecture, and extensive functionality for both administrators and passengers.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Screenshots](#screenshots)
- [Installation](#installation)
- [Usage](#usage)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [License](#license)
- [Author](#author)

## 🎯 Overview

The Bus Management System is a sophisticated desktop application designed to streamline bus transportation operations. Built with modern JavaFX technology and following object-oriented programming principles, this system provides a complete solution for managing bus reservations, passenger information, and administrative tasks.

### 🌟 Key Highlights

- **Modern GUI**: Beautiful, responsive interface with gradient backgrounds and modern styling
- **Role-based Access**: Separate dashboards for administrators and passengers
- **Real-time Updates**: Live seat availability and reservation status
- **Comprehensive Management**: Complete CRUD operations for buses, passengers, and reservations
- **Payment Integration**: Secure payment processing with invoice generation
- **Data Persistence**: File-based user management and reservation storage
- **Modular Architecture**: Clean separation of concerns with MVC pattern

## ✨ Features

### 🔐 Authentication System
- **Dual Login Interface**: Separate login portals for administrators and passengers
- **Secure Authentication**: File-based user credential management
- **Session Management**: Proper login/logout functionality

### 👥 User Management
- **Admin Dashboard**: Comprehensive administrative controls
- **Client Dashboard**: User-friendly passenger interface
- **Role-based Permissions**: Different access levels for different user types

### 🚌 Bus Management
- **Bus Registration**: Add new buses with detailed information
- **Route Management**: Define and manage bus routes
- **Capacity Control**: Set and monitor bus seating capacity
- **Real-time Status**: Live updates on bus availability

### 💺 Seat Reservation System
- **Interactive Seat Selection**: Visual seat map with availability status
- **Real-time Booking**: Instant seat reservation with conflict prevention
- **Reservation History**: Complete booking history for passengers
- **Cancellation Support**: Easy reservation cancellation with seat release

### 💳 Payment Processing
- **Secure Payment Gateway**: Credit card payment processing
- **Invoice Generation**: Automated invoice creation
- **Payment History**: Complete transaction records
- **Refund Management**: Automated refund processing for cancellations

### 📊 Advanced Features
- **Discount System**: Special discount application for eligible passengers
- **Reporting System**: Comprehensive reservation and revenue reports
- **Search & Filter**: Advanced search capabilities for buses and reservations
- **Data Validation**: Robust input validation and error handling

## 🏗️ Architecture

### Design Patterns Used
- **Model-View-Controller (MVC)**: Clean separation of business logic and UI
- **Observer Pattern**: Real-time updates across components
- **Factory Pattern**: Object creation and management
- **Singleton Pattern**: Shared resource management

### Core Components

#### 📁 Model Classes
- **`Bus.java`**: Comprehensive bus entity with seat management
- **`Passenger.java`**: Customer information and profile management
- **`Reservation.java`**: Booking details and status tracking
- **`Payment.java`**: Payment processing and transaction management
- **`Ticket.java`**: Ticket generation and validation
- **`Seat.java`**: Individual seat status and booking management
- **`Admin.java`**: Administrator functionality and permissions

#### 🎮 Controller Classes
- **`LoginController.java`**: Authentication and user management
- **`ClientDashboardController.java`**: Passenger interface management
- **`PaymentScreenController.java`**: Payment processing interface
- **`DiscountScreenController.java`**: Discount application logic
- **`ViewReservationsController.java`**: Reservation viewing and management

#### 🎨 View Components (FXML)
- **`LoginPage.fxml`**: Main authentication interface
- **`ClientDashboard.fxml`**: Passenger dashboard layout
- **`AdminDashboard.fxml`**: Administrator control panel
- **`PaymentScreen.fxml`**: Payment processing interface
- **`DiscountScreen.fxml`**: Discount application form
- **`ViewReservations.fxml`**: Reservation management interface

## 🖥️ Screenshots

### Login Interface
Beautiful gradient-based login interface with role selection

### Client Dashboard
Modern dashboard with bus listings, reservation options, and account management

### Admin Panel
Comprehensive administrative interface for system management

### Payment Processing
Secure payment gateway with credit card processing

## 🚀 Installation

### Prerequisites
- **Java 22** or higher
- **JavaFX 22** or higher
- **Apache Maven 3.8+**
- **Git** (for cloning the repository)

### Setup Instructions

1. **Clone the Repository**
   ```bash
   git clone https://github.com/inventixcity/bus-management-system.git
   cd bus-management-system
   ```

2. **Install Dependencies**
   ```bash
   mvn clean install
   ```

3. **Run the Application**
   ```bash
   mvn javafx:run
   ```

   **Alternative execution:**
   ```bash
   mvn clean compile exec:java
   ```

4. **Build Executable JAR**
   ```bash
   mvn clean package
   java -jar target/BusManagementSystem-1.0-SNAPSHOT.jar
   ```

## 📖 Usage

### For Administrators
1. **Login**: Use admin credentials to access the administrative dashboard
2. **Manage Buses**: Add, edit, or remove buses from the system
3. **View Reservations**: Monitor all passenger reservations
4. **Generate Reports**: Create comprehensive system reports

### For Passengers
1. **Login**: Use passenger credentials to access the client dashboard
2. **Browse Buses**: View available buses and routes
3. **Make Reservations**: Select seats and make bookings
4. **Payment**: Process payments securely
5. **Manage Bookings**: View and cancel existing reservations

### Default Credentials
The system comes with pre-configured user accounts in `users.txt`:
- **Admin**: Check the `users.txt` file for admin credentials
- **Passenger**: Check the `users.txt` file for passenger credentials

## 🔧 Configuration

### Application Properties
- **Main Class**: `com.example.busmanagementsystem.HelloApplication`
- **JavaFX Version**: 22-ea+11
- **Maven Compiler**: Java 22
- **Build Tool**: Apache Maven

### Customization Options
- **Database Configuration**: Modify data persistence layer
- **Payment Gateway**: Integrate with real payment processors
- **Styling**: Customize CSS files for different themes
- **Localization**: Add multi-language support

## 📚 API Documentation

### Core Classes and Methods

#### Bus Class
```java
public class Bus {
    // Seat management
    public void bookSeat(int seatNumber)
    public void freeSeat(int seatNumber)
    public List<Seat> getAvailableSeats()
    
    // Status and information
    public void displayStatus()
    public String getRouteDetails()
}
```

#### Reservation Class
```java
public class Reservation {
    // Reservation management
    public static Reservation makeReservation(int reservationID, Passenger passenger, Bus bus, int seatNumber)
    public void cancelReservation()
    public String viewReservation()
}
```

#### Payment Class
```java
public class Payment {
    // Payment processing
    public void processPayment()
    public void refundPayment()
    public void generateInvoice()
}
```

## 🤝 Contributing

We welcome contributions to the Bus Management System! Here's how you can help:

### Getting Started
1. **Fork the Repository**
2. **Create a Feature Branch**
   ```bash
   git checkout -b feature/amazing-feature
   ```
3. **Commit Your Changes**
   ```bash
   git commit -m 'Add some amazing feature'
   ```
4. **Push to the Branch**
   ```bash
   git push origin feature/amazing-feature
   ```
5. **Open a Pull Request**

### Development Guidelines
- Follow Java naming conventions
- Write comprehensive JavaDoc comments
- Include unit tests for new features
- Maintain consistent code formatting
- Update documentation as needed

### Bug Reports
Please use the GitHub issue tracker to report bugs. Include:
- Operating system and Java version
- Steps to reproduce the issue
- Expected vs actual behavior
- Screenshots if applicable

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2025 Umer Fraz Ahmed

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## 👨‍💻 Author

**Umer Fraz Ahmed**
- GitHub: [@inventixcity](https://github.com/inventixcity)
- Email: inventixcui@gmail.com

## 🙏 Acknowledgments

- JavaFX community for excellent documentation
- Maven for dependency management
- All contributors and testers

## 📈 Project Status

This project is actively maintained and continuously improved. Current version: **1.0.0**

### Roadmap
- [ ] Database integration (MySQL/PostgreSQL)
- [ ] Web-based interface
- [ ] Mobile app companion
- [ ] Real-time notifications
- [ ] Advanced analytics dashboard
- [ ] Multi-language support

---

⭐ **If you find this project useful, please consider giving it a star!** ⭐
