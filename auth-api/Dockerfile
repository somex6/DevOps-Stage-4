# Build stage
FROM golang:1.21 AS builder

WORKDIR /app

# Copy dependencies first to leverage Docker cache
COPY go.mod go.sum ./
RUN go mod download

# Copy source code
COPY . .

# Build static binary with CGO disabled
RUN CGO_ENABLED=0 go build -o auth-api

# Final stage using minimal Alpine image
FROM alpine:latest

# Install CA certificates for potential HTTPS communication
RUN apk --no-cache add ca-certificates
WORKDIR /app
COPY --from=builder /app/auth-api .

# Set default environment variables
ENV AUTH_API_PORT=8081
ENV USERS_API_ADDRESS="http://users-api:8083"
ENV JWT_SECRET="myfancysecret"

# Expose the default port (can be overridden)
EXPOSE 8081

# Run the application
CMD ["./auth-api"]