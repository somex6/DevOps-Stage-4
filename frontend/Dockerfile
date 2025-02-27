# # Production-ready Dockerfile
# FROM node:14 AS build

# RUN apt-get update && \
#     apt-get install -y python2 make g++ && \
#     ln -sf /usr/bin/python2 /usr/bin/python

# WORKDIR /app
# COPY package*.json ./
# RUN npm install node-sass@4.14.1 --save-dev
# RUN npm install
# COPY . .
# RUN npm run build

# FROM node:14-alpine
# WORKDIR /app
# COPY --from=build /app/dist ./dist
# COPY --from=build /app/node_modules ./node_modules

# # Install production dependencies only
# RUN npm install --production \
#     serve \
#     vue \
#     vue-router \
#     axios

# ENV PORT=8080
# ENV AUTH_API_ADDRESS=http://localhost:8081
# ENV TODOS_API_ADDRESS=http://localhost:8082
# ENV ZIPKIN_ADDRESS=http://localhost:9411

# EXPOSE $PORT
# CMD ["sh", "-c", "serve -s dist -l $PORT"]


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