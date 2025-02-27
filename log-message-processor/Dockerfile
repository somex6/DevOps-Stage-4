FROM python:3.9-alpine

WORKDIR /app

# Install GCC and required build dependencies
RUN apk add --no-cache gcc musl-dev python3-dev

# Copy requirements first to leverage Docker cache
COPY requirements.txt .

# Install dependencies
RUN pip3 install -r requirements.txt

# Copy the application code
COPY . .

# Set default environment variables
ENV REDIS_HOST=redis
ENV REDIS_PORT=6379
ENV REDIS_CHANNEL=log_channel

# Run the application
CMD ["python3", "main.py"]