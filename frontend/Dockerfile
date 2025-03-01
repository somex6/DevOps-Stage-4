# Use Node.js 14 as the base image
FROM node:14-alpine

# Set working directory
WORKDIR /app

# Copy package.json and package-lock.json
COPY package*.json ./

# Install dependencies
RUN npm install

# Copy the rest of the application code
COPY . .

# Set environment variables with defaults
ENV PORT=8080
ENV AUTH_API_ADDRESS=http://localhost:3000
ENV TODOS_API_ADDRESS=http://localhost:3001

# Expose the port the dev server will run on
EXPOSE ${PORT}

# Start the application in development mode
CMD ["npm", "run", "dev"]