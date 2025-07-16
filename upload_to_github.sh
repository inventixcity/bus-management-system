#!/bin/bash

# GitHub Upload Script for Bus Management System
# Author: Umer Fraz Ahmed
# Email: inventixcui@gmail.com

echo "🚀 Bus Management System - GitHub Upload Script"
echo "==============================================="

# Colors for output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Repository details
REPO_NAME="bus-management-system"
GITHUB_USERNAME="inventixcity"
REPO_URL="https://github.com/${GITHUB_USERNAME}/${REPO_NAME}.git"

echo -e "${BLUE}Repository: ${REPO_URL}${NC}"
echo ""

# Check if git is installed
if ! command -v git &> /dev/null; then
    echo -e "${RED}❌ Git is not installed. Please install Git first.${NC}"
    exit 1
fi

# Check if we're in a git repository
if [ ! -d ".git" ]; then
    echo -e "${RED}❌ This is not a Git repository. Please run from the project root.${NC}"
    exit 1
fi

echo -e "${GREEN}✅ Git repository detected${NC}"

# Check if there are any uncommitted changes
if [[ -n $(git status --porcelain) ]]; then
    echo -e "${YELLOW}⚠️  There are uncommitted changes. Committing them first...${NC}"
    git add .
    git commit -m "Update: Final changes before GitHub upload"
fi

echo -e "${GREEN}✅ All changes committed${NC}"

# Check if remote origin already exists
if git remote get-url origin &> /dev/null; then
    echo -e "${YELLOW}⚠️  Remote origin already exists. Updating...${NC}"
    git remote set-url origin $REPO_URL
else
    echo -e "${BLUE}📡 Adding remote origin...${NC}"
    git remote add origin $REPO_URL
fi

echo -e "${GREEN}✅ Remote origin configured${NC}"

# Push to GitHub
echo -e "${BLUE}🚀 Pushing to GitHub...${NC}"
echo "This will push your code to: $REPO_URL"
echo ""
echo -e "${YELLOW}⚠️  Make sure you have created the repository on GitHub first!${NC}"
echo "Repository name: $REPO_NAME"
echo "GitHub username: $GITHUB_USERNAME"
echo ""
read -p "Continue with push? (y/N): " -n 1 -r
echo

if [[ $REPLY =~ ^[Yy]$ ]]; then
    # Push to GitHub
    if git push -u origin main; then
        echo ""
        echo -e "${GREEN}🎉 SUCCESS! Your Bus Management System has been uploaded to GitHub!${NC}"
        echo ""
        echo -e "${BLUE}📋 Repository Details:${NC}"
        echo "  URL: $REPO_URL"
        echo "  Branch: main"
        echo "  Files: $(git ls-files | wc -l) files uploaded"
        echo ""
        echo -e "${BLUE}📝 Next Steps:${NC}"
        echo "  1. Visit: https://github.com/$GITHUB_USERNAME/$REPO_NAME"
        echo "  2. Add repository topics: java, javafx, desktop-application, bus-management"
        echo "  3. Create a release (v1.0.0)"
        echo "  4. Star your own repository"
        echo "  5. Share on LinkedIn/portfolio"
        echo ""
        echo -e "${GREEN}🌟 Your advanced Bus Management System is now live on GitHub!${NC}"
    else
        echo ""
        echo -e "${RED}❌ Failed to push to GitHub.${NC}"
        echo ""
        echo -e "${YELLOW}Common solutions:${NC}"
        echo "  1. Make sure the repository exists on GitHub"
        echo "  2. Check your internet connection"
        echo "  3. Verify your GitHub credentials"
        echo "  4. Try: git push origin main --force (if needed)"
        echo ""
        echo -e "${BLUE}Manual commands:${NC}"
        echo "  git remote -v"
        echo "  git push origin main"
        exit 1
    fi
else
    echo ""
    echo -e "${YELLOW}⚠️  Upload cancelled by user.${NC}"
    echo ""
    echo -e "${BLUE}To upload manually:${NC}"
    echo "  1. Create repository on GitHub: https://github.com/new"
    echo "  2. Run: git push -u origin main"
    echo ""
    echo -e "${BLUE}Repository is ready for upload!${NC}"
fi

echo ""
echo -e "${GREEN}Thank you for using the Bus Management System! 🚌✨${NC}"
