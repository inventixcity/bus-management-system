# Contributing to Bus Management System

Thank you for your interest in contributing to the Bus Management System! This document provides guidelines and instructions for contributing to this project.

## 🎯 Code of Conduct

By participating in this project, you are expected to uphold our code of conduct. Please be respectful and professional in all interactions.

## 🚀 How to Contribute

### Reporting Bugs

Before creating bug reports, please check the existing issues to avoid duplicates. When creating a bug report, include:

- **Clear description** of the issue
- **Steps to reproduce** the problem
- **Expected behavior** vs **actual behavior**
- **Environment details** (OS, Java version, etc.)
- **Screenshots** if applicable

### Suggesting Features

Feature requests are welcome! Please provide:

- **Clear description** of the feature
- **Use case** or problem it solves
- **Possible implementation** approach
- **Alternative solutions** considered

### Pull Requests

1. **Fork** the repository
2. **Create a feature branch** (`git checkout -b feature/amazing-feature`)
3. **Make your changes** following the coding standards
4. **Add tests** for new functionality
5. **Update documentation** as needed
6. **Commit your changes** (`git commit -m 'Add amazing feature'`)
7. **Push to the branch** (`git push origin feature/amazing-feature`)
8. **Open a Pull Request**

## 📝 Development Guidelines

### Code Style

- Follow Java naming conventions
- Use meaningful variable and method names
- Write comprehensive JavaDoc comments
- Maintain consistent indentation (4 spaces)
- Keep methods focused and concise

### Testing

- Write unit tests for new features
- Ensure all tests pass before submitting
- Test on multiple operating systems if possible
- Include integration tests for complex features

### Documentation

- Update README.md for new features
- Add JavaDoc comments for public methods
- Include inline comments for complex logic
- Update API documentation as needed

## 🛠️ Development Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/inventixcity/bus-management-system.git
   cd bus-management-system
   ```

2. **Install dependencies**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn javafx:run
   ```

4. **Run tests**
   ```bash
   mvn test
   ```

## 📋 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/busmanagementsystem/
│   │       ├── controllers/
│   │       ├── models/
│   │       └── views/
│   └── resources/
│       └── com/example/busmanagementsystem/
│           ├── fxml/
│           └── css/
└── test/
    └── java/
        └── com/example/busmanagementsystem/
```

## 🏷️ Commit Message Format

Use clear, descriptive commit messages:

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Types:**
- `feat`: New feature
- `fix`: Bug fix
- `docs`: Documentation changes
- `style`: Code style changes
- `refactor`: Code refactoring
- `test`: Adding or updating tests
- `chore`: Maintenance tasks

**Example:**
```
feat(reservation): Add seat selection interface

- Implement interactive seat map
- Add real-time seat availability
- Include booking confirmation dialog

Fixes #123
```

## 🔍 Review Process

All pull requests go through a review process:

1. **Automated checks** (build, tests, code quality)
2. **Code review** by maintainers
3. **Testing** on different platforms
4. **Documentation review**
5. **Merge** after approval

## 📚 Resources

- [Java Documentation](https://docs.oracle.com/en/java/)
- [JavaFX Documentation](https://openjfx.io/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Git Documentation](https://git-scm.com/doc)

## 💬 Communication

- **GitHub Issues**: For bug reports and feature requests
- **Pull Requests**: For code contributions
- **Discussions**: For questions and general discussion

## 🙏 Recognition

Contributors will be acknowledged in the README.md file and release notes.

## 📄 License

By contributing to this project, you agree that your contributions will be licensed under the MIT License.

---

Thank you for contributing to the Bus Management System! 🚌✨
