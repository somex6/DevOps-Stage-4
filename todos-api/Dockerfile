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

# Expose the port the API will run on (default to 3001)
EXPOSE 3001

# Start the application with nodemon for development
CMD ["npm", "start"]